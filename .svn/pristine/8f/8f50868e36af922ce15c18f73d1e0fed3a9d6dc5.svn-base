package com.tian.sakura.cdd.console.security;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.auth.TConRole;
import com.tian.sakura.cdd.db.domain.auth.TConUser;
import com.tian.sakura.cdd.db.manage.auth.RoleManage;
import com.tian.sakura.cdd.db.manage.auth.UserManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 将当前用户信息加载进UserDetailsService
 * 用于spring security管控后续验证信息
 *
 * @author dell
 */
@Service
public class CurrUserDetailLoad implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserManage userManage;
    @Autowired
    private RoleManage roleManage;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("普通登录-当前登录账号:" + username);
        return doBuildAuthorities(username);
    }

    private User doBuildAuthorities(String username) {
        //用户信息
        TConUser user = userManage.selectByUserNo(username);
        if (user == null) {
            throw new BizRuntimeException(RespCodeEnum.USERNAME_PASSWOED_ERRO);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<TConRole> roles = roleManage.selectByUserId(user.getId());
        for (TConRole role : roles) {
            //添加用户角色权限
            authorities.add(new SimpleGrantedAuthority(role.getRoleNo()));
        }
        User userDetails = new User(user.getUserNo(), user.getUserPasswd(), authorities);
        logger.info("用户角色权限：" + JSON.toJSONString(userDetails));

        return userDetails;
    }

}
