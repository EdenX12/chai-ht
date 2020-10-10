package com.tian.sakura.cdd.order.prd;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.order.context.PrdOrderPayCallBackContext;
import com.tian.sakura.cdd.order.context.TaskPayCallBackContext;
import com.tian.sakura.cdd.order.task.TaskOrderOptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 商品订单支付状态生命周期
 *
 * @author lvzonggang
 */

@Service
public class PrdOrderPaymentLifeCycle {

    @Autowired
    private PrdOrderOptService prdOrderOptService;


    public void cancelOrder(String orderId) {
        PrdOrderPayCallBackContext context = new PrdOrderPayCallBackContext();
        context.setOrderId(orderId);
        context.setPayStatus(EPayStatus.CANSEL);
        context.setPayTime(new Date());
        context.setRemark("用户取消订单");

        prdOrderOptService.changePaymentLife(context);
    }

    public void closeOrder(String orderId) {
        PrdOrderPayCallBackContext context = new PrdOrderPayCallBackContext();
        context.setOrderId(orderId);
        context.setPayStatus(EPayStatus.CLOSED);
        context.setOrderStatus(EOrderStatus.CLOSED);
        context.setPayTime(new Date());
        context.setRemark("订单超时关闭");

        prdOrderOptService.changePaymentLife(context);
    }

    public void payOrder(String orderId) {
        PrdOrderPayCallBackContext context = new PrdOrderPayCallBackContext();
        context.setOrderId(orderId);
        context.setOrderStatus(EOrderStatus.TO_BE_SEND);
        context.setPayStatus(EPayStatus.PAYED);
        context.setPayTime(new Date());

        prdOrderOptService.changePaymentLife(context);
    }
}
