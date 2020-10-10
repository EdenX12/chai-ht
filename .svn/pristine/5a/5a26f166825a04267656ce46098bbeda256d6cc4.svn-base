package com.tian.sakura.cdd.console.web.auth;


import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.entity.BaseQueryVo;
import com.tian.sakura.cdd.db.domain.auth.TConMenu;
import com.tian.sakura.cdd.db.domain.auth.vo.RolePremVo;
import com.tian.sakura.cdd.service.auth.ConMenuService;
import com.tian.sakura.cdd.service.auth.MenuPermService;
import com.tian.sakura.cdd.service.auth.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 维护菜单的基本信息（增，删，改，查）
 * 维护菜单对应的权限集（功能集）
 */
@RestController
@RequestMapping("menus")
public class ConMenuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConMenuService conMenuService;
    @Autowired
    private MenuPermService menuPermService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public void AddMenu(@RequestBody TConMenu tConMenu) {
        conMenuService.addMenu(tConMenu);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public void modificationMenu(@RequestBody TConMenu tConMenu) {
        conMenuService.updateMenu(tConMenu);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public void removeMenu(String id) {
        conMenuService.logicDeleteMenu(id);
    }

    @RequestMapping(value = "/find/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public TConMenu queryMenu(@PathVariable("id") String id) {    //这个参数是从模板路径拿到的
        TConMenu tConMenu = conMenuService.selectByPrimary(id);
        return tConMenu;
    }

    @RequestMapping(value = "/findByPage", method = {RequestMethod.GET, RequestMethod.POST})
    public PageInfo<TConMenu> findByPage(@RequestBody BaseQueryVo queryVo) {
        PageInfo<TConMenu> pageInfo = conMenuService.queryPage(queryVo);
        return pageInfo;
    }

    @RequestMapping(value = "/findListTree", method = {RequestMethod.GET, RequestMethod.POST})
    public List<TConMenu> findListTree() {
        List<TConMenu> menuList = conMenuService.selectMenuTree();
        return menuList;
    }

    @RequestMapping("selectPermsByMenuId")
    public Map<String, Object> selectPermsByMenuId(String menuId) {
        TConMenu menu = conMenuService.selectByPrimary(menuId);

        Map<String, Object> data = new HashMap<>();
        data.put("menu", menu);
        data.put("perms", permissionService.selectByMenuId(menuId));
        return data;
    }

    /**
     * 挂载权限
     *
     * @param conRolePremDTO
     * @return
     */
    @RequestMapping("mountPerm")
    public void mountPerm(@RequestBody RolePremVo conRolePremDTO) {
        menuPermService.mountPermission(conRolePremDTO.getPermIds(), conRolePremDTO.getMenuId());
    }

    /**
     * 卸载资源
     *
     * @param rolePremDTO
     * @return
     */
    @RequestMapping("dismountPerm")
    public void dismountPerm(@RequestBody RolePremVo rolePremDTO) {

        String premId = rolePremDTO.getPermId();
        List<String> premIds = Arrays.asList(new String[]{premId});
        menuPermService.dismountPermission(premIds, rolePremDTO.getMenuId());
    }
}
