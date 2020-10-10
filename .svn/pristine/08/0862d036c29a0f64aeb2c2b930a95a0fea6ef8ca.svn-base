package com.tian.sakura.cdd.srv.service.pay;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.EPayType;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.db.manage.order.OrderProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 商品订单支付回调
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class PrdOrderPayCallBack {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderManage orderManage;
    @Autowired
    private OrderDetailManage orderDetailManage;
    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private OrderProductManage orderProductManage;
    @Autowired
    private ProductSpecManage productSpecManage;

    /**
     * 回调
     *
     * 数据校验由上层完成，本方法只更新数据
     *
     * @param context
     */
    public void callback(PrdOrderPayCallBackContext context) {

        //支付状态
        EPayStatus payStatus = context.getPayStatus();
        // 支付成功
        if (payStatus == EPayStatus.PAYED) {
            doUpdateOrder(context);
            doUpdateOrderDetail(context);

            doUpdatePay(context);
        } else if (payStatus == EPayStatus.CANSEL) {
            // 取消支付
            doUpdateOrder(context);
            doUpdateOrderDetail(context);
            doUpdateOrderProduct(context);
            //还原库存
            doUpdatePrdStock(context);
            doUpdatePay(context);
        }
    }

    private void doUpdateOrder(PrdOrderPayCallBackContext context) {
        //更新状态
        Order updOrder = new Order();
        updOrder.setId(context.getOrderId());
        if (context.getPayType() != null) {
            updOrder.setPaymentType(context.getPayType());
        }
        updOrder.setPaymentState(context.getPayStatus().getCode());
        updOrder.setPaymentTime(context.getPayTime());
        orderManage.updateByPrimaryKeySelective(updOrder);
        logger.info("订单[id={}]下的支付状态[payStatus={}]修改完毕。",
                context.getOrderId(), context.getPayStatus());
    }

    private void doUpdateOrderDetail(PrdOrderPayCallBackContext context) {
        String orderId = context.getOrderId();
        //String orderDetailId = req.getBody().getOrderDetailId();
        List<OrderDetail> dbOrderDetailList = orderDetailManage.selectByOrderId(orderId);
        for (OrderDetail orderDetail : dbOrderDetailList) {
            //更新状态
            OrderDetail updRecord = new OrderDetail();
            updRecord.setId(orderDetail.getId());
            updRecord.setPaymentState(context.getPayStatus().getCode());
            updRecord.setOrderStatus(context.getOrderStatus().getCode());
            updRecord.setPaymentTime(context.getPayTime());
            updRecord.setCancelReason(context.getRemark());
            orderDetailManage.updateByPrimaryKeySelective(updRecord);
            logger.info("订单[id={}]下的订单明细状态[payStatus={},订单状态={}]修改完毕。",
                    orderId, context.getPayStatus(), context.getOrderStatus());
        }

    }

    private void doUpdatePay(PrdOrderPayCallBackContext context) {
        String orderId = context.getOrderId();
        UserPay dbUserPay = userPayManage.selectByRelationId(orderId);
        if (dbUserPay != null) {
            UserPay updUserPay = new UserPay();
            updUserPay.setId(dbUserPay.getId());
            updUserPay.setPayStatus(context.getPayStatus().getCode());
            updUserPay.setPayTime(context.getPayTime());
            if (context.getPayType() != null) {
                updUserPay.setPayType(context.getPayType());
            }
            updUserPay.setTransSn(context.getTransactionId());
            userPayManage.updateByPrimaryKeySelective(updUserPay);
            logger.info("订单[id={}]下的用户支付记录[payStatus={}]修改完毕。",
                    orderId, context.getPayStatus());
        }
    }

    private void doUpdateOrderProduct(PrdOrderPayCallBackContext context) {

    }

    private void doUpdatePrdStock(PrdOrderPayCallBackContext context) {
        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailManage.selectByOrderId(context.getOrderId());
        // 查询产品及购买数量
        for (OrderDetail orderDetail : orderDetailList) {
            //orderDetail.getId();
            List<OrderProduct> productList = orderProductManage.queryByOrderDetailId(orderDetail.getId());
            for (OrderProduct orderProduct : productList) {
                productSpecManage.increaseStock(
                        Integer.valueOf(orderProduct.getProductSpecId()),
                        orderProduct.getProductNumber());
            }

        }
        // 释放库存
    }
}
