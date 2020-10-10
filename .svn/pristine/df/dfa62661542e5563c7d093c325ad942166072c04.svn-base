package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.common.entity.Role;
import com.tian.sakura.cdd.common.util.FinalName;
import com.tian.sakura.cdd.db.dao.admin.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2019-06-10 21:29
 */
@Primary
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminRoleService {
    private final RoleMapper roleMapper;

    public boolean addNewRole(Role role) {
        if (!role.getName().startsWith(FinalName.ROLE_PREFIX)) {
            role.setName(FinalName.ROLE_PREFIX + role.getName());
        }
        return roleMapper.insertRole(role) == 1;
    }

    public List<Role> getAllRoles() {
        return roleMapper.selectAllRoles();
    }

    public boolean deleteRoleById(Long roleId) {
        // 因为在建 menu_role 表的时候，规定了 menu_role 表的列 role_id 与 role 表
        // 的 id 列是外键级联删除的，所以这里删除的时候，无需考虑 menu_role 表中还存在的关联关系
        return roleMapper.deleteRoleById(roleId) == 1;
    }
}
