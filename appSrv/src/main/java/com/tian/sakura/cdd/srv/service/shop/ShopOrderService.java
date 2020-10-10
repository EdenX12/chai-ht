package com.tian.sakura.cdd.srv.service.shop;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.EPickupFlag;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.express.Express;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.express.ExpressManage;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.srv.service.pay.PrdOrderPayCallBack;
import com.tian.sakura.cdd.srv.service.pay.PrdOrderPayCallBackContext;
import com.tian.sakura.cdd.srv.service.pay.PrdOrderPayCallBackContextBuilder;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderCancelReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReqBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderSendReq;

@Service
@Transactional
public class ShopOrderService {
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private OrderDetailManage orderDetailManage;
	@Autowired
	private ExpressManage expressManage;
    @Autowired
    private OrderManage orderManage;
    @Autowired
    private PrdOrderPayCallBack orderPayCallBack;


	// 商家置已提货状态（客户自提货已提走）
	public void setPickupFlag(SUser user, PrdOrderDetailQueryReqBody req) {
		// 根据用户查商铺
		ShopUser ss = shopUserManage.findByUserId(user.getId());
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		OrderDetail o = orderDetailManage.selectByPrimary(req.getId());
		if (o == null || Integer.valueOf(o.getShopId()) != ss.getShopId()) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有数据权限");
		}

		// 修改自提货状态为已提货
		OrderDetail detailOrder = new OrderDetail();
		detailOrder.setId(req.getId());
		detailOrder.setPickupFlag(EPickupFlag.HAS_PICKEDUP.getCode());
		orderDetailManage.updateByPrimaryKeySelective(detailOrder);

	}

	/**
	 * 发货
	 *
	 * @param user
	 * @param req
	 */
	public void sendPrdOrder(SUser user, PrdOrderSendReq req) {
		// 根据用户查商铺
		ShopUser ss = shopUserManage.findByUserId(user.getId());
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		// check
		OrderDetail dbOrderDetail = orderDetailManage.selectByPrimary(req.getBody().getOrderDetailId());

		if (dbOrderDetail != null) {
			// TODO check shopId
			if (Integer.valueOf(dbOrderDetail.getShopId()) != ss.getShopId()) {
				throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有数据权限");
			}

			// check status
			if (dbOrderDetail.getOrderStatus() != EOrderStatus.TO_BE_SEND.getCode()) {
				throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, "订单发货");
			}
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(dbOrderDetail.getId());
			orderDetail.setShippingCode(req.getBody().getShippingCode());
			orderDetail.setShippingExpressId(req.getBody().getShippingExpressId());
			Express express = expressManage.selectByPrimary(req.getBody().getShippingExpressId().toString());
			if (express != null) {
				orderDetail.setShippingExpressCode(express.getECode());
			}
			orderDetail.setShippingTime(new Date());
			orderDetail.setOrderStatus(EOrderStatus.SENDED.getCode());
			orderDetailManage.updateByPrimaryKeySelective(orderDetail);
		}
	}

    /**
          * 商家取消订单
     *  1.根据
     * @param user
     * @param req
     */
    public void cancelPrdOrder(SUser user, PrdOrderCancelReq req) {
		// 根据用户查商铺
        String userId = user.getId();
    	ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
        String orderId = req.getBody().getOrderId();
        Order dbOrder = orderManage.selectByPrimary(orderId);
        if (dbOrder.getPaymentState() != EPayStatus.TO_BE_PAY.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, "取消订单");
        }
         //根据总订单id查询明细订单
        List<OrderDetail> details =  orderDetailManage.selectByOrderId(orderId);
        for(OrderDetail detail:details) {
        	Integer orderStatus = detail.getOrderStatus();
        	String shopIdStr = detail.getShopId();
        	if(ss.getShopId() != Integer.valueOf(shopIdStr)) {
        		throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该订单包含多个商家的商品，必须由买家自己取消");
        	}
        }
        
        PrdOrderPayCallBackContext context = PrdOrderPayCallBackContextBuilder.buildForCancel(orderId,req.getBody().getCancelReason());
        orderPayCallBack.callback(context);

    }

}
