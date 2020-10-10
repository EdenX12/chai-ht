package com.tian.sakura.cdd.order.prd.status;

import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.db.manage.order.OrderProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.order.context.PrdOrderPayCallBackContext;
import com.tian.sakura.cdd.order.prd.PrdOrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public abstract class BasePrdOrderStatusService implements PrdOrderStatusService {
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


    protected void doUpdateOrder(PrdOrderPayCallBackContext context) {
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

    protected void doUpdateOrderDetail(PrdOrderPayCallBackContext context) {
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

    protected void doUpdatePay(PrdOrderPayCallBackContext context) {
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

    protected void doUpdateOrderProduct(PrdOrderPayCallBackContext context) {

    }

    protected void doUpdatePrdStock(PrdOrderPayCallBackContext context) {
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
