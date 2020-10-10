package com.tian.sakura.cdd.order.prd.status;

import com.tian.sakura.cdd.order.context.PrdOrderPayCallBackContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service("prdOrderCancelOptService")
@Transactional
public class PrdOrderCancelOptService  extends BasePrdOrderStatusService {


    @Override
    public void changePaymentLife(PrdOrderPayCallBackContext context) {
        // s_order  pay_status = 3
        doUpdateOrder(context);
        // s_order_detail  pay_status = 3
        doUpdateOrderDetail(context);

        //还原库存
        doUpdatePrdStock(context);
        // s_user_pay pay_status = 3
        doUpdatePay(context);
    }
}
