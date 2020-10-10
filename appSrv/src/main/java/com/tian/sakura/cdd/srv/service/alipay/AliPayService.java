package com.tian.sakura.cdd.srv.service.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 * @Date 2019/8/28
 */
@Service
public class AliPayService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private ParamsService paramsService;

    public Map<String,String> pay(BaseAlipayMsg alipayMsg) {
        Map<String, String> orderInfoMap = new HashMap<>();
        //调用alipay接口
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(alipayMsg.getGoodsDes());
        model.setSubject(alipayMsg.getGoodsTitle());

        model.setOutTradeNo(alipayMsg.getOutTradeNo());
        model.setTimeoutExpress("1d"); //30m
        BigDecimal totalAmt = alipayMsg.getTotalAmt();
        //TODO 测试使用
         //totalAmt = new BigDecimal("0.01");


        model.setTotalAmount(totalAmt.setScale(2,RoundingMode.HALF_UP).toString());
        model.setProductCode("QUICK_MSECURITY_PAY");//固定值
        request.setBizModel(model);
        String url = paramsService.getValueNoCache("alipay_noticy_url");
        request.setNotifyUrl(url);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            logger.info(JSON.toJSONString(response));
            logger.info("orderString-[{}]",response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            orderInfoMap.put("orderInfo", response.getBody());
        } catch (AlipayApiException e) {
            throw new BizRuntimeException(e.getErrCode(),e.getErrMsg(),e);
        }

        return orderInfoMap;
    }
}
