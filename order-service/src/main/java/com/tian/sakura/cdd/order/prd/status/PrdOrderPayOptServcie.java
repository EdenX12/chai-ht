package com.tian.sakura.cdd.order.prd.status;

import com.tian.sakura.cdd.order.context.PrdOrderPayCallBackContext;
import org.springframework.stereotype.Service;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service("prdOrderPayOptServcie")
public class PrdOrderPayOptServcie extends BasePrdOrderStatusService{

    @Override
    public void changePaymentLife(PrdOrderPayCallBackContext context) {
        // 更新 s_order
        doUpdateOrder(context);
        // 更新 s_order_detail
        doUpdateOrderDetail(context);
        // 更新 s_user_pay
        doUpdatePay(context);
    }
}
