package com.tian.sakura.cdd.console.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;
	
	@Autowired
    public void setMyAccessDecisionManager() {
		super.setAccessDecisionManager(myAccessDecisionManager);
    }
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//logger.info("MyFilterSecurityInterceptor 请求过滤器开始===========");
		FilterInvocation fi = new FilterInvocation(request, response, chain);

      	//fi里面有一个被拦截的url
      	//里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
      	//再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
        	//执行下一个拦截器
        	fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
        	super.afterInvocation(token, null);
        }

	}

	@Override
	public void destroy() {

	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

}
