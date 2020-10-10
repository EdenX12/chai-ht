package com.tian.sakura.cdd.security;

import com.tian.sakura.cdd.common.entity.User;
import com.tian.sakura.cdd.common.util.FinalName;
import com.tian.sakura.video.service.auth.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 咸鱼
 * @date 2019-06-03 20:18
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbUserDetailServiceImpl implements UserDetailsService {

    private final AdminUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(FinalName.USERNAME_NOT_FOUND);
        }
        return user;
    }
}
