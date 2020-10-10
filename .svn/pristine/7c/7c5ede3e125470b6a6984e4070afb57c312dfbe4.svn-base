package com.tian.sakura.cdd.wx.api.pay;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.wx.api.BaseWxApi;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderReq;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderRsp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class WxPayApi extends BaseWxApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PAY_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private XStream initReq(){
        //保证转化成xml中的下划线和属性保持一致
        XStream xstream = new XStream(new Xpp3Driver(new NoNameCoder()));
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[] {UnifiedOrderReq.class, UnifiedOrderRsp.class});
        xstream.processAnnotations(UnifiedOrderReq.class);
        xstream.processAnnotations(UnifiedOrderRsp.class);
        return xstream;
    }


    public String unifiedOrder(UnifiedOrderReq req) {
        XStream xstream = initReq();

        String parameter =  xstream.toXML(req);;
        String rtnValue = mockPostReturnStrValue(PAY_UNIFIED_ORDER_URL, parameter);
        logger.info("rtnValue=" + rtnValue);
        UnifiedOrderRsp rsp = (UnifiedOrderRsp)xstream.fromXML(rtnValue);
        if (StringUtils.equals(rsp.getReturn_code(), "FAIL")) {
            throw new BizRuntimeException(rsp.getReturn_code(), rsp.getReturn_msg());
        }

        if (StringUtils.equals(rsp.getResult_code(), "FAIL")) {
            throw new BizRuntimeException(rsp.getErr_code(), rsp.getErr_code_des());
        }

        return rsp.getPrepay_id();
    }

    public static void main(String[] args) {
        UnifiedOrderReq req = UnifiedOrderReq.builder()
                .appid("wx2421b1c4370ec43b")
                .mchId("10000100")
                .body("TEST")
                .nonceStr("1add1a30ac87aa2db72f57a2375d8fec")
                .notifyUrl("http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php")
                .build();
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[] {UnifiedOrderReq.class, UnifiedOrderRsp.class
        });
        xStream.processAnnotations(UnifiedOrderReq.class);
        xStream.processAnnotations(UnifiedOrderRsp.class);
        String parameter =  xStream.toXML(req);
        System.out.println(parameter);


        String xml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[APP]]></trade_type>\n" +
                "</xml>";
        //XStream xStream2 = new XStream();
        //xStream2.processAnnotations(UnifiedOrderRsp.class);
        //xStream.fromXML(xml);
        UnifiedOrderRsp rsp = (UnifiedOrderRsp)xStream.fromXML(xml);
        System.out.println(JSON.toJSONString(rsp));
    }
}
