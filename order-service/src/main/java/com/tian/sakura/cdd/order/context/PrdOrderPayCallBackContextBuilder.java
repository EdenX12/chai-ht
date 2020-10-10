package com.tian.sakura.cdd.order.context;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.EPayStatus;

import java.util.Date;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class PrdOrderPayCallBackContextBuilder {

    public static PrdOrderPayCallBackContext buildForPayed(String orderId) {
        PrdOrderPayCallBackContext context = new PrdOrderPayCallBackContext();
        context.setOrderId(orderId);
        context.setOrderStatus(EOrderStatus.TO_BE_SEND);
        context.setPayStatus(EPayStatus.PAYED);
        context.setPayTime(new Date());
        //context.setRemark("余额支付");

        return context;
    }

    public static PrdOrderPayCallBackContext buildForCancel(String orderId, String reason) {
        PrdOrderPayCallBackContext context = new PrdOrderPayCallBackContext();
        context.setOrderId(orderId);
        context.setOrderStatus(EOrderStatus.CANCLE);
        context.setPayStatus(EPayStatus.CANSEL);
        context.setPayTime(new Date());
        context.setRemark(reason);

        return context;
    }
}
