package com.tian.sakura.cdd.wx.api;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.util.HttpClientUtils;

/**
 * 微信api基类
 *
 * @author lvzonggang
 */
public class BaseWxApi {

    public <T> T mockGet(String url, Class<T> clz) {
        String repsonse = HttpClientUtils.mockGet(url);
        repsonse = repsonse != null ? repsonse : "";
        if (repsonse.contains("errcode")) {
            throw new RuntimeException(String.format("微信接口调用异常【mes=%s】", repsonse));
        } else {
            return JSON.parseObject(repsonse, clz);
        }
    }

    public String mockPostReturnStrValue(String url, String parameter) {
        return HttpClientUtils.mockPost(url, parameter);
    }
}
