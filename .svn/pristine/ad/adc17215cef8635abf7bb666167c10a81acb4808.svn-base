package com.tian.sakura.cdd.wx.api.user;

import com.tian.sakura.cdd.wx.api.BaseWxApi;
import com.tian.sakura.cdd.wx.message.WxUser;

/**
 * 微信用户信息的api管理类
 *
 * @author lvzonggang
 */
public class UserinfoApi extends BaseWxApi {

    public WxUser obtainUser(String token, String openid) {
        String url_format = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        String url = String.format(url_format, token, openid);

        return mockGet(url, WxUser.class);
    }
}
