package com.tian.sakura.cdd.console.security;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.web.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出登录的处理器
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("退出登陆");
        logger.info(JSON.toJSONString(SecurityContextHolder.getContext()));
        logger.info(JSON.toJSONString(authentication));

        response.setContentType("application/json;charset=UTF-8");
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        CommonResult clientResponse = CommonResult.ok();
        response.getWriter().write(JSON.toJSONString(clientResponse));
        logger.info("LogoutSuccessHandler：" + JSON.toJSONString(clientResponse));
    }

}
