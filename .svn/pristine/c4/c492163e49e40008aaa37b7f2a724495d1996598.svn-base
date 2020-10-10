package com.tian.sakura.cdd.wx.message.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * <xml>
 *   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
 *   <attach><![CDATA[支付测试]]></attach>
 *   <bank_type><![CDATA[CFT]]></bank_type>
 *   <fee_type><![CDATA[CNY]]></fee_type>
 *   <is_subscribe><![CDATA[Y]]></is_subscribe>
 *   <mch_id><![CDATA[10000100]]></mch_id>
 *   <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
 *   <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
 *   <out_trade_no><![CDATA[1409811653]]></out_trade_no>
 *   <result_code><![CDATA[SUCCESS]]></result_code>
 *   <return_code><![CDATA[SUCCESS]]></return_code>
 *   <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
 *   <time_end><![CDATA[20140903131540]]></time_end>
 *   <total_fee>1</total_fee>
 * <coupon_fee><![CDATA[10]]></coupon_fee>
 * <coupon_count><![CDATA[1]]></coupon_count>
 * <coupon_type><![CDATA[CASH]]></coupon_type>
 * <coupon_id><![CDATA[10000]]></coupon_id>
 * <coupon_fee><![CDATA[100]]></coupon_fee>
 *   <trade_type><![CDATA[JSAPI]]></trade_type>
 *   <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
 * </xml>
 *
 * @author lvzonggang
 */

@Setter
@Getter
@XStreamAlias("xml")
public class PayNoticeMsg {
    // SUCCESS/FAIL
    private String return_code;
    private String return_msg;
    // SUCCESS/FAIL
    private String result_code;
    private String err_code;
    private String err_code_des;

    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String sign_type;
    private String openid;
    private String is_subscribe;
    private String trade_type;
    private String bank_type;
    private int total_fee;
    private int settlement_total_fee;
    // CNY
    private String fee_type;
    private int cash_fee;
    private int coupon_fee;
    private int coupon_count;

    // 微信支付订单号
    private String transaction_id;
    // 商户订单号
    private String out_trade_no;
    private String attach;
    // 支付完成时间  支付完成时间，格式为yyyyMMddHHmmss
    private String time_end;

}
