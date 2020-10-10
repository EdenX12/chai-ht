package com.tian.sakura.cdd.srv.service.pay;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付回调上下文
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class BasePayCallBackContext {
    private String userId;
    // 支付id
    private String userPayId;
    // 支付方式
    private Integer payType;
    //支付完成时间
    private Date payTime;
    // 微信或支付保的订单号
    private String transactionId;
    //订单金额
    private BigDecimal orderFee;

    private EPayStatus payStatus;

    private String remark;
}
