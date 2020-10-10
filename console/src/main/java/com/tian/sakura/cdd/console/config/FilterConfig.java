package com.tian.sakura.cdd.console.config;

import com.tian.sakura.cdd.common.web.filter.LogMdcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 日志MDC 参数
 *
 * @author lvzonggang
 */
@Configuration
public class FilterConfig {

    @Bean("logMdcFilter")
    public Filter logMdcFilter() {
        return new LogMdcFilter();
    }

    /**
     * 配置过滤器
     *
     * 数字越小，优先级越高；
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(logMdcFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        registration.setName("logMdcFilter");
        return registration;
    }
}
