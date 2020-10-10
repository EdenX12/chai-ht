package com.tian.sakura.cdd.wx.support;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import com.tian.sakura.cdd.wx.message.pay.PayNoticeMsg;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class WxMessageXstreamHelper {

    public static XStream initPayNoticeXstream(){
        //保证转化成xml中的下划线和属性保持一致
        XStream xstream = new XStream(new Xpp3Driver(new NoNameCoder()));
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[] {PayNoticeMsg.class});
        xstream.processAnnotations(PayNoticeMsg.class);
        return xstream;
    }

    public static PayNoticeMsg toPayNoticeMsg(String xml) {
        XStream xstream = initPayNoticeXstream();
        return (PayNoticeMsg)xstream.fromXML(xml);
    }

    public static void main(String[] args) {
        String xml = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +

                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml>";

        PayNoticeMsg payNoticeMsg = toPayNoticeMsg(xml);
        System.out.println(payNoticeMsg.getOut_trade_no());
    }
}
