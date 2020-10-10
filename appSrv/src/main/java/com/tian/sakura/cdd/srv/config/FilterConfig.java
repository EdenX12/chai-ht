package com.tian.sakura.cdd.srv.config;

import com.google.common.collect.Maps;
import com.tian.sakura.cdd.common.web.filter.LogMdcFilter;
import com.tian.sakura.cdd.common.web.filter.XssFilter;
import com.tian.sakura.cdd.srv.filter.ApiTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 配置过滤器,日志线程号
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

    /**
     * xss过滤拦截器
     */
//    @Bean
//    public FilterRegistrationBean xssFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new XssFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addUrlPatterns("/*");
//        Map<String, String> initParameters = Maps.newHashMap();
//        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*, /wxpay/notice, /alipay/notice");
//        initParameters.put("isIncludeRichText", "true");
//        filterRegistrationBean.setInitParameters(initParameters);
//        return filterRegistrationBean;
//    }


}
