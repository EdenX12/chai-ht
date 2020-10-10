package com.tian.sakura.cdd.console.security;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.auth.vo.RoleResourceVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, Collection<ConfigAttribute>> map = null;

    /**
     * 启动时加载一次，加载所有url和权限（或角色）的对应关系
     */
    public void loadResourceDefine() {
        logger.info("加载所有权限");
        map = new HashMap<>();

        ConfigAttribute cfg;
        List<RoleResourceVo> roleResources = new ArrayList<>();

        Collection<ConfigAttribute> array;
        for (RoleResourceVo resource : roleResources) {
            String resourceUrl = resource.getResourceUrl();
            if (StringUtils.isEmpty(resourceUrl)) continue;
            if (map.containsKey(resourceUrl)) {
                array = map.get(resourceUrl);
                array.add(new SecurityConfig(resource.getRoleNo()));
            } else {
                array = new ArrayList<>();
                array.add(new SecurityConfig(resource.getRoleNo()));
            }
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(resourceUrl, array);
        }

    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     * 用来判定用户是否有此权限。如果不在权限表中则放行。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //if(map ==null){
        loadResourceDefine();
        logger.info("url和角色对应关系：" + JSON.toJSONString(map));
        //}
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
