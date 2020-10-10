package com.tian.sakura.cdd.console.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 判定是否拥有权限的决策者
	 * 检查用户是否够权限访问资源
	 * 	参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
	 * 	参数object是url,包含客户端发起的请求的requset信息，
	 * 	可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
	 *	参数configAttributes所需的权限
	 *
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		String url = ((FilterInvocation) object).getRequestUrl();
		/*
		 * configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
		 * 此方法是为了判定用户请求的url 是否在权限表中，
		 * 如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行
		 */
		if(null== configAttributes || configAttributes.size() <=0) {
			//throw new AccessDeniedException("请求资源："+url+"没有访问权限");
            return;
        }
        ConfigAttribute c;
        String needRole;
        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
			//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("请求资源："+url+"没有访问权限");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
