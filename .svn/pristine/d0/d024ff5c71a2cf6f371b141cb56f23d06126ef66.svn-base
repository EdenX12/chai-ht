package com.tian.sakura.cdd.srv.web.paynotice;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.tian.sakura.cdd.srv.service.alipay.AlipayDbParameters;
import com.tian.sakura.cdd.srv.service.paynotice.AlipayNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Controller
@RequestMapping("alipay")
public class AlipayNoticeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AlipayDbParameters alipayProperties;

    @Autowired
    private AlipayNoticeService alipayNoticeService;

    @RequestMapping("notice")
    public void  noticePay(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        //设置编码格式为UTF-8
        resp.setCharacterEncoding("UTF-8");
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        resp.setHeader("content-type", "text/html;charset=UTF-8");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        logger.info(JSON.toJSONString(params));
        String message = "success";
        try {
            boolean flag = AlipaySignature.rsaCertCheckV1(params, alipayProperties.getAlipayPublicKey(), "UTF-8","RSA2");
            logger.info(">>>>>>>>>>" + flag);
            if (flag) {
                //支付成功
                alipayNoticeService.notice(params);
            } else {
                logger.info("支付宝支付验签失败>>>>>>>>>>" + flag);
                message = "failure";
            }

        } catch (AlipayApiException e) {
            logger.info("支付宝API接口异常>>>>>>>>>>");
            logger.error(e.getMessage(), e);
            message = "failure";
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            message = "failure";
        } finally {
            outPutPrintWriter(message, resp);
        }
    }

    /**
     *
     * @方法名: outPutPrintWriter
     * @描述: 使用PrintWriter输出流输出数据
     *
     * @param  message
     * @param resp
     * @throws IOException
     * @创建人 zender
     */
    private void outPutPrintWriter(String message, HttpServletResponse resp) throws Exception {
        //获取PrintWriter输出流
        PrintWriter out = resp.getWriter();
        //使用PrintWriter流向客户端输出字符
        out.write(message);
    }
}
