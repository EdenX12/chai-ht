package com.tian.sakura.cdd.order.prd;

import com.tian.sakura.cdd.order.context.PrdOrderPayCallBackContext;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public interface PrdOrderStatusService {

    /**
     * 处理s_order, s_order_detail, s_order_product
     * @param context
     */
    void changePaymentLife(PrdOrderPayCallBackContext context);
}
