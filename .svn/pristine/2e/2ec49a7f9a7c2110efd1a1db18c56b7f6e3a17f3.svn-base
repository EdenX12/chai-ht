package com.tian.sakura.cdd.console.security;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.web.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * access-denied-handler
 * 配置一个AccessDeniedHandler的实现类. 这个类的作用是, 当一个已授权(或已登陆)的用户请求访问他权限之外的资源时, 这个类的handle方法将会被调用, 定义如何处理这个请求.
 * <p>
 * <p>
 * 注意AccessDeniedHandler与AuthenticationEntryPoint的区别:
 * AccessDeniedHandler: 已授权的用户请求权限之外的资源时会交给这个类处理.
 * AuthenticationEntryPoint: 未授权的用户请求非公共资源时会交给这个类处理.
 *
 * @author dell
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info(userDetails.getUsername() + "权限访问失败");
        response.setContentType("application/json;charset=UTF-8");
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        CommonResult clientResponse = CommonResult.fail(RespCodeEnum.NO_ACCESS_RIGHTS);

        logger.info("AccessDenied：" + JSON.toJSONString(clientResponse));
        response.getWriter().write(JSON.toJSONString(clientResponse));
    }

}
