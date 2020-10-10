package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.common.dict.*;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import com.tian.sakura.cdd.db.domain.user.UserAddress;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecValueManage;
import com.tian.sakura.cdd.db.manage.user.UserAddressManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.web.order.dto.OrderPrdBody;
import com.tian.sakura.cdd.srv.web.order.dto.OrderShopPrdBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderCreateReq;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class PrdOrderBuilder {
    private static Logger logger = LoggerFactory.getLogger(PrdOrderBuilder.class);
    @Autowired
    private UserAddressManage addressManage;
    @Autowired
    private ParamsService paramsService;

    @Autowired
    private ProductManage productManage;
    @Autowired
    private ProductSpecManage productSpecManage;
    @Autowired
    private ProductSpecValueManage productSpecValueManage;

    public static Order buildNewOrder(String userId, PrdOrderCreateReq req) {
        Order order = new Order();
        order.setId(IdGenUtil.uuid());
        order.setPayAmount(req.getBody().getPayAmount());
        order.setChannel(req.getHead().getChannel());
        order.setCreateTime(new Date());
        order.setUserId(userId);
        order.setPaymentState(EPayStatus.TO_BE_PAY.getCode());
        return order;
    }

    public List<OrderDetail> buildNewOrderDetail(String userId, String orderId, PrdOrderCreateReq req) {
        List<OrderShopPrdBody> prdBodyList = req.getBody().getOrderShopPrdBodyList();

        UserAddress userAddress = addressManage.selectByPrimary(req.getBody().getAddressId());
        String addressDetail = new StringBuilder(userAddress.getProvinceName())
                .append(" ").append(userAddress.getCityName())
                .append(" ").append(userAddress.getAreaName())
                .append(" ").append(userAddress.getAreaInfo()).toString();
        logger.info("详细地址-{}", addressDetail);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        prdBodyList.forEach(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(IdGenUtil.uuid());
            orderDetail.setOrderId(orderId);
            orderDetail.setShopId(item.getShopId());
            orderDetail.setUserId(userId);
            orderDetail.setOrderSn(IdGenUtil.generateId());
            orderDetail.setPaymentState(EPayStatus.TO_BE_PAY.getCode());
            orderDetail.setOrderAmount(item.getOrderAmount());
            orderDetail.setShippingFee(item.getShippingFee());
            orderDetail.setCouponAmount(item.getCouponAmount());
            orderDetail.setUserCouponId(item.getUserCouponId());
            orderDetail.setPayAmount(item.getPayAmount());
            //orderDetail.setDeliverExplain(item.getDeliverExplain());
            orderDetail.setAddressId(req.getBody().getAddressId());
            orderDetail.setOrderStatus(EOrderStatus.TO_BE_PAY.getCode());
            orderDetail.setAddressName(userAddress.getTrueName());
            orderDetail.setAddressPhone(userAddress.getMobPhone());
            orderDetail.setAddressDetail(addressDetail);
            orderDetail.setChannel(req.getHead().getChannel());
            orderDetail.setCreateTime(new Date());

            //产品
            List<OrderProduct> orderProductList = new ArrayList<>();
            Map<String, Integer> prdStockMap = new HashMap<>();
            List<OrderPrdBody> orderPrdBodyList = item.getOrderPrdBodyList();
            orderPrdBodyList.forEach(prdBody -> {
                OrderProduct orderProduct = new OrderProduct();
                Product dbProduct = productManage.selectByPrimary(prdBody.getProductId());
                ProductSpec productSpec = productSpecManage
                        .selectByProductIdAndSpecValueIds(prdBody.getProductId(), prdBody.getProductSpecId());
                if (productSpec == null) {
                    throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "商品规格");
                }
                prdStockMap.put(productSpec.getId(), prdBody.getProductNumber());
                orderProduct.setId(IdGenUtil.uuid());
                orderProduct.setUserId(userId);
                orderProduct.setOrderDetailId(orderDetail.getId());
                orderProduct.setProductId(prdBody.getProductId());
                //  规格id
                orderProduct.setProductSpecId(productSpec.getId());
                orderProduct.setProductSpecValueName(productSpec.getProductSpecValueName());
                orderProduct.setProductNumber(prdBody.getProductNumber());
                orderProduct.setProductPrice(productSpec.getProductPrice());
                orderProduct.setScribingPrice(productSpec.getScribingPrice());
                orderProduct.setTotalReward(dbProduct.getTotalReward());
                orderProduct.setTaskPrice(dbProduct.getTaskPrice());
                orderProduct.setProductName(dbProduct.getProductName());
                orderProduct.setProductDes(dbProduct.getProductDes());
                orderProduct.setProductImg(dbProduct.getProductImg());
                orderProduct.setProductDetail(dbProduct.getProductDetail());
                orderProduct.setCreateTime(new Date());

                orderProductList.add(orderProduct);
            });
            orderDetail.setPrdStockMap(prdStockMap);
            orderDetail.setOrderProductList(orderProductList);

            orderDetailList.add(orderDetail);
        });

        return orderDetailList;
    }

    public UserPay buildNewUserPay(String userId, String orderId, PrdOrderCreateReq req) {
        UserPay userPay = new UserPay();
        userPay.setId(IdGenUtil.uuid());
        userPay.setPaySn(IdGenUtil.generateId());
        userPay.setUserId(userId);
        userPay.setRelationId(orderId);
        if (req.getBody().getTotalAmount() == null) {
            BigDecimal shipAmt = req.getBody().getShippingFee();
            BigDecimal payAmt = req.getBody().getPayAmount();
            userPay.setTotalAmount(shipAmt == null ? req.getBody().getPayAmount() : shipAmt.add(payAmt));
        } else {
            userPay.setTotalAmount(req.getBody().getTotalAmount());
        }

        userPay.setPayType(EPayBizType.PAY_ORDER.getCode());
        userPay.setPayAmount(req.getBody().getPayAmount());
        userPay.setPayStatus(EPayStatus.TO_BE_PAY.getCode());
        userPay.setCreateTime(new Date());
        return userPay;
    }



    public static EOrderQueryStatus toQueryOrderStatus(int orderStatus) {
        //待付款
        if (orderStatus == EOrderStatus.TO_BE_PAY.getCode()) {
            return EOrderQueryStatus.TO_BE_PAY;
        }
        // 待发货
        else if (orderStatus == EOrderStatus.TO_BE_SEND.getCode()) {
            return EOrderQueryStatus.TO_BE_SEND;
        }
        //  待收货
        else if (orderStatus == EOrderStatus.SENDED.getCode()) {
            return EOrderQueryStatus.TO_BE_RECEIVE;
        }
        // 退还货
        else if (orderStatus == EOrderStatus.APPLY_BACK.getCode()
                || orderStatus == EOrderStatus.BACKED.getCode()) {
            return EOrderQueryStatus.BACK_OR_CHANGE;
        }
        // 已完成
        else if (orderStatus == EOrderStatus.CONFIRM_RECEIVED.getCode()
                || orderStatus == EOrderStatus.CLOSED.getCode()) {
            return EOrderQueryStatus.CLOSED;
        }

        return null;

    }
}
