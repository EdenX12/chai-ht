package com.tian.sakura.cdd.srv.config;

import com.tian.sakura.cdd.common.util.BeanMapTransUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderReq;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderRsp;
import com.tian.sakura.cdd.wx.service.WxPayService;
import com.tian.sakura.cdd.wx.util.WxUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static com.tian.sakura.cdd.srv.GlobalConstants.PARAM_KEY_OPEN_APP_ID;
import static com.tian.sakura.cdd.srv.GlobalConstants.PARAM_KEY_WX_PAY_MCH_ID;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxApiConfigTest {
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private ParamsService paramsService;

    @Test
    public void pay() throws Exception {
        UnifiedOrderReq req = UnifiedOrderReq.builder()
                .appid("wxfbe18a53c8235ade")
                .mchId("1245640902")
                .nonceStr(IdGenUtil.uuid())
                .body("测试单子")
                .outTradeNo(IdGenUtil.generateId())
                .totalFee("0.1")
                .spbillCreateIp("127.0.0.1")
                .notifyUrl("http://www.weixin.qq.com/wxpay/pay.php")
                .tradeType("APP")
                .build();
        Map<String, String> map = BeanMapTransUtils.transBean2Map(req, false);
        String key = "e69220eaa44247c4aa28a135b539f30b";
        String sign = WxUtil.generateSignature(map, key);
        System.out.println("sign|" + sign);
        req.setSign(sign(req, key));
        System.out.println(wxPayService.unifiedOrder(req));
    }

    private String sign(UnifiedOrderReq req, String key) {
        String toSign = new StringBuilder("appid=").append(req.getAppid())
                .append("&body=").append(req.getBody())
                .append("&mch_id=").append(req.getMchId())
                .append("&nonce_str=").append(req.getNonceStr())
                .append("&notify_url=").append(req.getNotifyUrl())
                .append("&out_trade_no=").append(req.getOutTradeNo())
                .append("&spbill_create_ip=").append(req.getSpbillCreateIp())
                .append("&total_fee=").append(req.getTotalFee())
                .append("&trade_type=").append(req.getTradeType())
                .append("&key=").append(key)
                .toString();
        System.out.println("待签名字符串|" + toSign);
        String sign = MD5Util.md5Hex(toSign).toUpperCase();
        System.out.println("sign|" + sign);


        return sign;
    }
}
