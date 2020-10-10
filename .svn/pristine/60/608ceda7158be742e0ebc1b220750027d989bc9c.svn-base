package com.tian.sakura.cdd.console.web;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.auth.TConMenu;
import com.tian.sakura.cdd.service.auth.ConMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConMenuService conMenuService;

    @RequestMapping(value = "/loadMenuAndUser")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        logger.info("Principal:" + JSON.toJSONString(authentication.getPrincipal()));

        //获取当前登录者角色信息
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        List<String> roleNoList = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roleNoList.add(authority.getAuthority());
        }
        List<TConMenu> menuList = conMenuService.selectByRoleNosWithPerm(roleNoList);

        Map<String, Object> mp = new HashMap<>();
        mp.put("auth", authentication.getPrincipal());
        mp.put("menus", menuList);

        return mp;
    }
}
