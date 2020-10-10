package com.tian.sakura.cdd.wx.service;

import com.tian.sakura.cdd.wx.api.pay.WxPayApi;
import com.tian.sakura.cdd.wx.message.pay.PayCallerMsg;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderReq;

/**
 *
 *
 * @author lvzonggang
 */
public class WxPayService {

    private WxPayApi wxPayApi;

    /**
     * 统一下单接口
     *
     * @param req
     * @return
     */
    public String unifiedOrder(UnifiedOrderReq req) {
        return wxPayApi.unifiedOrder(req);
    }

    public void setWxPayApi(WxPayApi wxPayApi) {
        this.wxPayApi = wxPayApi;
    }
}
