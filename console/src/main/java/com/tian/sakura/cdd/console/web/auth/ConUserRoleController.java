package com.tian.sakura.cdd.console.web.auth;

import com.tian.sakura.cdd.db.domain.auth.TConRole;
import com.tian.sakura.cdd.db.domain.auth.TConUser;
import com.tian.sakura.cdd.db.domain.auth.vo.MountRoleVo;
import com.tian.sakura.cdd.db.domain.auth.vo.UserRoleVo;
import com.tian.sakura.cdd.service.auth.ConRoleService;
import com.tian.sakura.cdd.service.auth.ConUserService;
import com.tian.sakura.cdd.service.auth.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 维护用户和角色的关联关系
 */
@RestController
@RequestMapping("userRole")
public class ConUserRoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConUserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ConRoleService roleService;


    @RequestMapping(value = "/selectByUserId/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> selectByRoleId(@PathVariable String userId) {
        TConUser user = userService.selectByPrimaryKey(userId);
        List<TConRole> roles = roleService.selectByUserId(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("roles", roles);
        return data;
    }

    /**
     * 挂载角色
     *
     * @param userRoleDTO
     * @return
     */
    @RequestMapping(value = "/mountRole", method = {RequestMethod.GET, RequestMethod.POST})
    public void mountRole(@RequestBody UserRoleVo userRoleDTO) {
        userRoleService.mountRole(userRoleDTO.getUserId(), userRoleDTO.getRoles());
    }


    /**
     * 卸载角色（单笔）
     *
     * @param mountRoleVo
     * @return
     */
    @RequestMapping("dismountRole")
    public void dismountRole(@RequestBody MountRoleVo mountRoleVo) {
        userRoleService.dismountRole(mountRoleVo.getUserId(), mountRoleVo.getRoleId());
    }

}
