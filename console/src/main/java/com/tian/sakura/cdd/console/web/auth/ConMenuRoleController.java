package com.tian.sakura.cdd.console.web.auth;

import com.tian.sakura.cdd.db.domain.auth.TConMenu;
import com.tian.sakura.cdd.db.domain.auth.TConMenuRole;
import com.tian.sakura.cdd.db.domain.auth.TConPermission;
import com.tian.sakura.cdd.db.domain.auth.vo.MenuRoleVo;
import com.tian.sakura.cdd.db.domain.auth.vo.MountPermissionVo;
import com.tian.sakura.cdd.service.auth.ConMenuService;
import com.tian.sakura.cdd.service.auth.MenuPermService;
import com.tian.sakura.cdd.service.auth.MenuRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 维护角色与菜单的关联关系
 * 维护角色，菜单，权限的关联关系（功能按钮）
 */
@RestController
@RequestMapping("menuRole")
public class ConMenuRoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuRoleService menuRoleService;

    @Autowired
    private ConMenuService menuService;

    @Autowired
    private MenuPermService menuPermService;

    @RequestMapping(value = "/select", method = {RequestMethod.POST, RequestMethod.GET})
    public List<TConMenuRole> selectAllData() {
        List<TConMenuRole> tConMenuRoles = menuRoleService.selectAllData();
        return tConMenuRoles;
    }

    @RequestMapping(value = "/selectByRoleId", method = {RequestMethod.POST, RequestMethod.GET})
    public List<TConMenu> selectByRoleId(String roleId) {
        List<TConMenu> tConMenuRoles = menuService.selectByRoleId(roleId);
        return tConMenuRoles;
    }

    /**
     * 先删除再添加
     *
     * @param conMenuRoleDTO
     * @return
     */
    @RequestMapping(value = "/addMenuRole", method = {RequestMethod.POST})
    public void mountMenu(@RequestBody MenuRoleVo conMenuRoleDTO) {
        menuRoleService.delectByRoleIdAndInsert(conMenuRoleDTO.getMenuIds(), conMenuRoleDTO.getRoleId());
    }

    @RequestMapping(value = "/dismountMenu", method = {RequestMethod.POST, RequestMethod.GET})
    public void dismountMenu(@RequestBody MenuRoleVo dismountMenuVo) {
        menuRoleService.dismountMenu(dismountMenuVo.getRoleId(), dismountMenuVo.getMenuId());
    }

    /***********************************************************************************************/
    /***********************************   角色菜单权限    *****************************************/
    /***********************************************************************************************/

    //查询角色，菜单下已维护的权限
    @RequestMapping(value = "/findPermsByRoleIdAndMenuId")
    public List<TConPermission> findPermsByRoleIdAndMenuId(String roleId, String menuId) {
        return menuPermService.findAllByRoleIdAndMenuId(roleId, menuId);
    }

    //挂载角色，菜单下的权限数据
    @RequestMapping(value = "/mountRoleMenuPerm")
    public void mountRoleMenuPerm(@RequestBody MountPermissionVo mountPermissionVo) {
        menuRoleService.mountRoleMenuPerm(mountPermissionVo);
    }

    //卸载角色。菜单下的权限数据
    @RequestMapping(value = "/dismountRoleMenuPerm/{id}")
    public void dismountRoleMenuPerm(String id) {
        menuRoleService.dismountRoleMenuPerm(id);
    }

}