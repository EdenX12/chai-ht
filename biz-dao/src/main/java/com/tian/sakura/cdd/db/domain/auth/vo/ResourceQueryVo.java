package com.tian.sakura.cdd.db.domain.auth.vo;

import com.tian.sakura.cdd.common.entity.BaseQueryVo;

/**
 * 资源查询参数vo
 *
 * @author lvzonggang
 * @Date 2019/4/4
 */
public class ResourceQueryVo extends BaseQueryVo {

    private String resourceName;
    private String resourceUrl;
    private String permissionId;
    private String menuId;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
