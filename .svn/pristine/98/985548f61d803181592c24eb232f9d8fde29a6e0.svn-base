package com.tian.sakura.cdd.srv.config;

import com.tian.sakura.cdd.common.web.CommonResult;
import com.tian.sakura.cdd.common.web.ResponseMessage;
import com.tian.sakura.cdd.common.web.ResponseMessageBuilder;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回处理的配置类
 *
 * @author lvzonggang
 * @Date 2020/2/25
 */
@EnableWebMvc
@Configuration
public class UnifiedReturnConfig {

    @RestControllerAdvice("com.tian.sakura.cdd.srv")
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            final String returnType = methodParameter.getParameterType().getName();
            ResponseMessage result = null;
            if ("void".equals(returnType)) {
                result = ResponseMessageBuilder.instance().ok();
            } else if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
                return body;
            } else if (body instanceof ResponseMessage){
                result = (ResponseMessage)body;
            } else {
                result = ResponseMessageBuilder.instance().body(body);
            }
            LoginUserThreadLocal.removeLoginUser();
            return result;
        }
    }
}
