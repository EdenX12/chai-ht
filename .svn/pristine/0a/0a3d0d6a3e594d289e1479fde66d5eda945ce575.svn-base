package com.tian.sakura.cdd.console.web.auth;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.db.domain.auth.TConUser;
import com.tian.sakura.cdd.db.domain.auth.vo.UserQueryVo;
import com.tian.sakura.cdd.service.auth.ConUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 维护用户基本信息（增，改，查，重置密码）
 */
@RestController
@RequestMapping("users")
public class ConUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConUserService userService;


    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public void AddUser(@RequestBody TConUser tConUser) {
        userService.addUser(tConUser);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public void updateUser(@RequestBody TConUser tConUser) {
        userService.updateUser(tConUser);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public void deleteUser(@PathVariable String id) {
        // userService.logicDeleteUser(id);
    }

    @RequestMapping(value = "/selectByPage", method = {RequestMethod.GET, RequestMethod.POST})
    public PageInfo<TConUser> selectByFuzzy(@RequestBody UserQueryVo userQueryVo) {
        PageInfo<TConUser> pageInfo = userService.selectByFuzzy(userQueryVo);
        return pageInfo;
    }

    @RequestMapping(value = "/resetPwd/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public void resetPwd(@PathVariable String id) {
        userService.resetPwd(id);
    }
}
