package com.tian.sakura.cdd.srv.web.demo;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.alipay.AliPayService;
import com.tian.sakura.cdd.srv.service.alipay.BaseAlipayMsg;
import com.tian.sakura.video.service.core.QRCodeCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private QRCodeCore qrCodeCore;
    @Autowired
    private AliPayService aliPayService;

    @RequestMapping("auth")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
    public Object auth() {
        Map<String,String> result = new HashMap<>();
        SUser user = LoginUserThreadLocal.getLoginUser();
        result.put("rsp","app test successful!");
        return result;
    }

    @RequestMapping("test")
    public Object test() {
        Map<String,String> result = new HashMap<>();
        qrCodeCore.encode("https://www.baidu.com");
        result.put("rsp","app test successful!");
        return result;
    }

    @RequestMapping("alipay")
    public Map<String, String> alipay() {
        BaseAlipayMsg alipayMsg = new BaseAlipayMsg();
        alipayMsg.setGoodsDes("拆多多");
        alipayMsg.setGoodsTitle("拆多多");
        alipayMsg.setGoodsType("2");
        alipayMsg.setOutTradeNo(IdGenUtil.generateId());
        alipayMsg.setTotalAmt(new BigDecimal("0.01"));
        //alipayMsg.setFeeRate();
        //alipayMsg.setNoticeUrl("htt");

         return aliPayService.pay(alipayMsg);
    }

    @RequestMapping("fail")
    public Object fail() {
        throw new BizRuntimeException("99999999","测试异常处理");
    }
}
