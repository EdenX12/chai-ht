package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.common.entity.Menu;
import com.tian.sakura.cdd.common.exception.BaseException;
import com.tian.sakura.cdd.db.dao.admin.MenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2019-06-04 16:52
 */
@Service
@Primary
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(rollbackFor = RuntimeException.class)
public class AdminMenuService {
    private final MenuMapper menuMapper;

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Menu> getMenusByUserId(Long userId) {
        return menuMapper.getMenusByUserId(userId);
    }

    public List<Menu> getMenuTree() {
        return menuMapper.getMenuTree();
    }

    public boolean addMenu(Menu menu) {
        if (selectMenu(menu) != null) {
            throw new BaseException("该菜单名称已经存在！");
        }
        menu.setEnabled(true);
        return menuMapper.insertMenu(menu) == 1;
    }

    public boolean removeMenusById(Long id) {
        Menu menu = new Menu();
        menu.setId(id);
        if (selectMenu(menu) == null) {
            throw new BaseException("该菜单不存在，无法删除！");
        }
        /*
         * 备注1：不用关心 parentId = #{id}的情况，因为 parentId 和 id 是级联删除的
         * 备注1：不用关心menu_role中与之相关联的条目，因为是级联删除的
         */
        return menuMapper.deleteMenu(id) > 0;
    }

    private Menu selectMenu(Menu menu) {
        return menuMapper.selectMenu(menu);
    }
}
