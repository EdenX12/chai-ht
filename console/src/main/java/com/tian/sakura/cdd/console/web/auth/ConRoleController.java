package com.tian.sakura.cdd.console.web.auth;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.db.domain.auth.TConRole;
import com.tian.sakura.cdd.db.domain.auth.vo.RoleQueryVo;
import com.tian.sakura.cdd.service.auth.ConRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 维护角色基本信息
 */
@RestController
@RequestMapping("roles")
public class ConRoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConRoleService conRoleService;


    @RequestMapping(value = "/findAllByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public PageInfo<TConRole> findAllByPage(@RequestBody RoleQueryVo roleQueryVo) {
        PageInfo<TConRole> pageInfo = conRoleService.selectAllByPage(roleQueryVo);
        return pageInfo;
    }

    @RequestMapping(value = "/findAll", method = {RequestMethod.POST, RequestMethod.GET})
    public List<TConRole> findAll() {
        List<TConRole> tConRoles = conRoleService.selectAllData();
        return tConRoles;
    }

    @RequestMapping(value = "/deleteRole/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteRole(@PathVariable String id) {
        conRoleService.logicDeleteRole(id);
    }

    @RequestMapping(value = "/addRole", method = {RequestMethod.POST, RequestMethod.GET})
    public TConRole addRole(@RequestBody TConRole tConRole) {
        TConRole tRole = conRoleService.addRole(tConRole);
        return tRole;
    }

    @RequestMapping(value = "/updateRole", method = {RequestMethod.POST, RequestMethod.GET})
    public void updateRole(@RequestBody TConRole tConRole) {
        conRoleService.updateRole(tConRole);
    }

    @RequestMapping(value = "/insertList", method = {RequestMethod.POST, RequestMethod.GET})
    public void insertList(@RequestBody List<TConRole> tConRole) {
        conRoleService.insertList(tConRole);
    }
}
