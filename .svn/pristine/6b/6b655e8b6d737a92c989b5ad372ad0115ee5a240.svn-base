package com.tian.sakura.cdd.console.security;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.web.CommonResult;
import com.tian.sakura.cdd.service.auth.ConMenuService;
import com.tian.sakura.cdd.service.auth.ConUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权成功的处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConMenuService conMenuService;
    @Autowired
    private ConUserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        User user = (User) authentication.getPrincipal();
        logger.info(user.getUsername() + "认证成功");
        response.setContentType("application/json;charset=UTF-8");
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        CommonResult clientResponse = CommonResult.ok();
        response.getWriter().write(JSON.toJSONString(clientResponse));
        logger.info("AuthenticationSuccess：" + JSON.toJSONString(clientResponse));
    }

}
