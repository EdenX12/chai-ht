package com.tian.sakura.cdd.wx.message.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
@Builder

@XStreamAlias("xml")
public class UnifiedOrderReq {
    /** 微信开放平台审核通过的应用APPID */
    private String appid;
    /** 微信支付分配的商户号*/
    @XStreamAlias("mch_id")
    private String mchId;
    @XStreamAlias("nonce_str")
    private String nonceStr;

    private String sign;
    @XStreamAlias("sign_type")
    private String signType;

    private String body;
    /**  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一  */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    //订单总金额，单位为分
    @XStreamAlias("total_fee")
    private String totalFee;
    // 终端IP
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    //notify_url 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
    @XStreamAlias("notify_url")
    private String notifyUrl;
    // 交易类型
    @XStreamAlias("trade_type")
    private String tradeType;


}
