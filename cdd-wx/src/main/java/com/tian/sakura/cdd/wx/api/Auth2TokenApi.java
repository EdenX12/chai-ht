package com.tian.sakura.cdd.wx.api;


import com.tian.sakura.cdd.wx.message.AuthTokenMsg;

/**
 * 通过code获取access_token
 * <p>
 * http请求方式: GET
 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
 * </p>
 *
 * @author lvzonggang
 */
public class Auth2TokenApi extends BaseWxApi {

    /**
     * 第三方通过code进行获取access_token的时候需要用到，code的超时时间为10分钟，一个code只能成功换取一次access_token即失效。
     * code的临时性和一次保障了微信授权登录的安全性。
     * 第三方可通过使用https和state参数，进一步加强自身授权登录的安全性。
     *
     * <p>正常实例</p>
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     * <p>异常实例
     * <p>
     * "errcode":40029,"errmsg":"invalid code"
     *
     * </p>
     *
     * @param appid
     * @param secret
     * @param code
     * @return
     */
    public AuthTokenMsg accessTokenByCode(String appid, String secret, String code) {
        String url_format = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        String url = String.format(url_format, appid, secret, code);

        return mockGet(url, AuthTokenMsg.class);
    }
}
