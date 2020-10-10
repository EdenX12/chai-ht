package com.tian.sakura.cdd.wx.service;

import com.tian.sakura.cdd.wx.api.Auth2TokenApi;
import com.tian.sakura.cdd.wx.api.user.UserinfoApi;
import com.tian.sakura.cdd.wx.message.AuthTokenMsg;
import com.tian.sakura.cdd.wx.message.WxUser;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class WxAuthService {

    private Auth2TokenApi auth2TokenApi;
    private UserinfoApi userinfoApi;

    public WxUser loginApp(String appid, String secret, String code) {
        AuthTokenMsg authTokenMsg = auth2TokenApi.accessTokenByCode(appid,secret,code);

        return userinfoApi.obtainUser(authTokenMsg.getAccess_token(), authTokenMsg.getOpenid());
    }

    public void setAuth2TokenApi(Auth2TokenApi auth2TokenApi) {
        this.auth2TokenApi = auth2TokenApi;
    }

    public void setUserinfoApi(UserinfoApi userinfoApi) {
        this.userinfoApi = userinfoApi;
    }
}
