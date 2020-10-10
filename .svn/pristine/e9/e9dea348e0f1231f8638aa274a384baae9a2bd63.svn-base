package com.tian.sakura.cdd.console.web.auth;


import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.db.domain.auth.TConPermission;
import com.tian.sakura.cdd.db.domain.auth.vo.MountResourceVo;
import com.tian.sakura.cdd.db.domain.auth.vo.PermissionQueryVo;
import com.tian.sakura.cdd.db.domain.auth.vo.ResourceQueryVo;
import com.tian.sakura.cdd.service.auth.PermissionService;
import com.tian.sakura.cdd.service.auth.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限api
 * <p>维护权限基本信息：增，改，删，详情查询，分页查询，</p>
 * <p>维护权限和资源关联关系：挂载资源，卸载资源，查询权限下的资源</p>
 *
 * @author lvzonggang
 * @Date 2019/4/9
 */

@RestController
@RequestMapping("permissions")
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("findByPage")
    public PageInfo<TConPermission> findByPaged(@RequestBody PermissionQueryVo queryVo) {
        return permissionService.findPaged(queryVo);
    }

    @RequestMapping("add")
    public void add(@RequestBody TConPermission permission) {
        permissionService.add(permission);
    }

    @RequestMapping("update")
    public void update(@RequestBody TConPermission permission) {
        permissionService.update(permission);
    }

    @RequestMapping("delete/{id}")
    public void delete(@PathVariable("id") String id) {
        permissionService.logicDelete(id);
    }

    @RequestMapping("findByPrimaryKey/{id}")
    public TConPermission findByPrimaryKey(@PathVariable("id") String id) {
        TConPermission permission = permissionService.findByPrimaryKey(id);
        return permission;
    }

    @RequestMapping("selectResourceByPermissionId")
    public Map<String, Object> selectResourceByPermissionId(@RequestBody ResourceQueryVo queryVo) {
        TConPermission permission = permissionService.findByPrimaryKey(queryVo.getPermissionId());
        Map<String, Object> data = new HashMap<>();
        data.put("permission", permission);
        data.put("resources", resourceService.selectResourceByPermissionId(queryVo));
        return data;
    }

    /**
     * 挂载资源
     *
     * @param mountResourceVo
     * @return
     */
    @RequestMapping("mountResource")
    public void mountResource(@RequestBody MountResourceVo mountResourceVo) {
        permissionService.mountResource(mountResourceVo);
    }

    /**
     * 卸载资源
     *
     * @param mountResourceVo
     * @return
     */
    @RequestMapping("dismountResource")
    public void dismountResource(@RequestBody MountResourceVo mountResourceVo) {
        permissionService.dismountResource(mountResourceVo.getPermissionId(), mountResourceVo.getResourceList());
    }
}
