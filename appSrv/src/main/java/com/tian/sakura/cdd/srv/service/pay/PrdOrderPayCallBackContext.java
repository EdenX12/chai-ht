package com.tian.sakura.cdd.srv.service.pay;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

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
