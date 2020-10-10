package com.tian.sakura.cdd.srv.config;

import com.tian.sakura.cdd.wx.api.Auth2TokenApi;
import com.tian.sakura.cdd.wx.api.pay.WxPayApi;
import com.tian.sakura.cdd.wx.api.user.UserinfoApi;
import com.tian.sakura.cdd.wx.service.WxAuthService;
import com.tian.sakura.cdd.wx.service.WxPayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信api配置
 *
 * @author lvzonggang
 */
@Configuration
public class WxApiConfig {
    @Bean
    public WxAuthService wxAuthService() {
        WxAuthService wxAuthService = new WxAuthService();

        wxAuthService.setAuth2TokenApi(auth2TokenApi());
        wxAuthService.setUserinfoApi(userinfoApi());
        return wxAuthService;
    }

    @Bean
    public WxPayService wxPayService() {
        WxPayService wxPayService = new WxPayService();
        wxPayService.setWxPayApi(wxPayApi());
        return wxPayService;
    }

    @Bean
    public Auth2TokenApi auth2TokenApi() {
        return new Auth2TokenApi();
    }

    @Bean
    public UserinfoApi userinfoApi() {
        return new UserinfoApi();
    }

    @Bean
    public WxPayApi wxPayApi() {
        return new WxPayApi();
    }
}
