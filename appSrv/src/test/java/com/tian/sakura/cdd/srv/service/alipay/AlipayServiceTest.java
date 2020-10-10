package com.tian.sakura.cdd.srv.service.alipay;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.srv.service.paynotice.AlipayNoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class AlipayServiceTest {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private AlipayNoticeService alipayNoticeService;


    @Test
    public void notice() {
        Map<String,String> param = new HashMap<>();

        String json = "{\"gmt_create\":\"2020-05-30 18:06:26\",\"charset\":\"UTF-8\",\"seller_email\":\"liuhg@phoneboss.cn\",\"subject\":\"拆哆哆商户平台\",\"sign\":\"CrceBZrX1xiMBqZuvdGDoo3Esh5oNDCSiXp6AknwtxD3IISd38SJwf+19tcNer1wsZYfWhYhQyFhCS7iZFzw0ENStkWCPzCFKPABkUSdAYNTTaLJYqdX8abyrJxyPkG6P5/hBHBCox86ldlzZsDhW/ymYazKw3R3wJZG1nhaneUOjnBBp3rC5g3ZIA+LeRBEkieLTg7ilIVIyvnCIaz7rjFP+VwGFREha7rVYCrBUp9aHk7ymVLFGTvXhcwR7DIW1n2j2bTYEME75PwGzsz7MNXqFXX1S3SHIGyA28NzCi/5ldQHsvjuqPOBhsRNLT4mUKcyN71fWgKeUcEbAPdi7w==\",\"body\":\"1266672232776724480\",\"buyer_id\":\"2088702013834981\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2020053000222180628034981457044570\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.01\",\"app_id\":\"2021001155693890\",\"buyer_pay_amount\":\"0.01\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088831156757573\",\"gmt_payment\":\"2020-05-30 18:06:28\",\"notify_time\":\"2020-05-30 18:06:29\",\"version\":\"1.0\",\"out_trade_no\":\"1266672232776724480\",\"total_amount\":\"0.01\",\"trade_no\":\"2020053022001434981410483005\",\"auth_app_id\":\"2021001155693890\",\"buyer_logon_id\":\"159****5391\",\"point_amount\":\"0.00\"}";
        param = JSON.parseObject(json, Map.class);
        alipayNoticeService.notice(param);
    }


    @Test
    public void pay() {
        BaseAlipayMsg alipayMsg = new BaseAlipayMsg();
        alipayMsg.setGoodsDes("拆多多");
        alipayMsg.setGoodsTitle("拆多多");
        alipayMsg.setGoodsType("2");
        alipayMsg.setOutTradeNo(IdGenUtil.generateId());
        alipayMsg.setTotalAmt(new BigDecimal("0.01"));
        //alipayMsg.setFeeRate();
        //alipayMsg.setNoticeUrl("htt");

        aliPayService.pay(alipayMsg);
    }

}
