package com.tian.sakura.cdd.srv.web.paynotice;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.srv.service.paynotice.WxPayNoticeService;
import com.tian.sakura.cdd.wx.message.pay.PayNoticeMsg;
import com.tian.sakura.cdd.wx.support.WxMessageXstreamHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Controller
@RequestMapping("wxpay")
public class WxPayNoticeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String OK = "SUCCESS";

    @Autowired
    private WxPayNoticeService wxPayNoticeService;
    /**
     *  <xml>
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
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("notice")
    public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            InputStream inputStream = request.getInputStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
            String str = writer.toString();
            logger.info("收到的通知：" + str);

            //解析数据
            PayNoticeMsg payNoticeMsg = WxMessageXstreamHelper.toPayNoticeMsg(str);
            // 此字段是通信标识，非交易标识，
            if (StringUtils.equals(OK,payNoticeMsg.getReturn_code())) {
                // 交易是否成功需要查看result_code来判断
                if (StringUtils.equals(OK, payNoticeMsg.getResult_code())) {
                    wxPayNoticeService.notic(payNoticeMsg);

                } else {
                    logger.warn("支付通知失败- 支付回调的返回码{}-{}",
                            payNoticeMsg.getErr_code(), payNoticeMsg.getErr_code_des());
                }
            } else {
                logger.warn("支付通知失败- 支付回调通知返回码{},返回信息{}",
                        payNoticeMsg.getReturn_code(), payNoticeMsg.getReturn_msg());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new BizRuntimeException(RespCodeEnum.SYSTEM_EXCEPTION);
        } finally {
            outPutPrintWriter(buildOk(), response);
        }
    }

    private String buildOk() {
        return "<xml>\n" +
                "\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }

    private void outPutPrintWriter(String message, HttpServletResponse resp) throws Exception {
        //获取PrintWriter输出流
        PrintWriter out = resp.getWriter();
        //使用PrintWriter流向客户端输出字符
        out.write(message);
    }
}
