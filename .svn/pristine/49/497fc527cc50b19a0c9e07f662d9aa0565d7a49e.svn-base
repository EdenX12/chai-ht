package com.tian.sakura.cdd.srv.web.order.dto;

import com.tian.sakura.cdd.wx.message.pay.PayCallerMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class PrdOrderPayRspBody {

    @ApiModelProperty("微信支付调用的信息报文")
    private PayCallerMsg wxPayMsg;

    @ApiModelProperty("支付宝支付调用的封装报文体")
    private String aliPayMsg;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;
    @ApiModelProperty("返现金额")
    private BigDecimal rtnAmt;
    @ApiModelProperty("奖励豆")
    private Integer beanCnt;
}
