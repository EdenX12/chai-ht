package com.tian.sakura.cdd.security;

import com.tian.sakura.cdd.common.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 咸鱼
 * @date 2019-06-04 19:56
 */
public class UserUtils {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(null);
        return user;
    }
}
