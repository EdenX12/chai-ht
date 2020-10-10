package com.tian.sakura.cdd.console.config;

import com.tian.sakura.cdd.common.web.CommonResult;
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

    @RestControllerAdvice("com.tian.sakura.cdd.console")
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            final String returnType = methodParameter.getParameterType().getName();

            if ("void".equals(returnType)) {
                return CommonResult.ok();
            }

            if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
                return body;
            }

            if (body instanceof CommonResult){
                return body;
            }

            return new CommonResult<Object>(body);
        }
    }
}
