package com.tian.sakura.cdd.order.context;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单支付回调上下文
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class PrdOrderPayCallBackContext extends BasePayCallBackContext{
    // 订单id
    private String orderId;

    private EOrderStatus orderStatus;

}
