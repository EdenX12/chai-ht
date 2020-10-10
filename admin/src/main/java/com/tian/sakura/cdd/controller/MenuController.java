package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.entity.Menu;
import com.tian.sakura.cdd.security.UserUtils;
import com.tian.sakura.video.service.auth.AdminMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2019-06-04 17:23
 */
@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {
    private final AdminMenuService menuService;
    @RequestMapping("/system")
    public ResultDto getSysMenus() {
        List<Menu> menus = menuService.getMenusByUserId(UserUtils.getCurrentUser().getId());
        if (!CollectionUtils.isEmpty(menus)) {
            return ResultDto.success().setObj(menus);
        }
        return ResultDto.error("获取菜单失败！");
    }
}
