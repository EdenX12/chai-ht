package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dto.UserDto;
import com.tian.sakura.cdd.common.entity.User;
import com.tian.sakura.cdd.common.exception.BaseException;
import com.tian.sakura.cdd.common.util.FinalName;
import com.tian.sakura.cdd.db.dao.admin.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2018/12/13 22:41
 */
@Service
@Primary
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(rollbackFor = RuntimeException.class)
public class AdminUserService {

    private final UserMapper userMapper;

    public boolean save(User user) {
        return userMapper.insertUser(user) == 1;
    }

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public UserDto getUsersByKeyWords(String keyWords, Integer pageNum, Integer pageSize) {
        if (StringUtils.isEmpty(keyWords.trim())) {
            keyWords = FinalName.USER_SEARCH_WORD_ALL;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.getUsersByKeyWords(keyWords);
        PageInfo<User> userInfo = new PageInfo<>(users);
        UserDto userDto = new UserDto();
        userDto.setUsers(users);
        userDto.setTotal(userInfo.getTotal());
        return userDto;
    }

    public boolean updateUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userMapper.updateUser(user) == 1;
    }

    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    public boolean updateUserRoles(Long userId, Long[] roleIds) {
        userMapper.deleteRolesByUserId(userId);
        return userMapper.updateUserRoles(userId, roleIds) == roleIds.length;
    }

    public boolean removeUser(Long id) {
        if (userMapper.selectUserById(id) == null) {
            throw new BaseException("无该用户！");
        }
        return userMapper.deleteUserById(id) == 1;
    }

    public boolean addUser(User user) {
        User tempUser = new User();
        tempUser.setUsername(user.getUsername());
        if (selectUser(tempUser) != null) {
            throw new BaseException("用户名已存在，请重新设置！");
        }
        // 和 WebSecurityConfig 密码配置策略相一致
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        user.setUserface(FinalName.DEFAULT_USER_FACE);
        return userMapper.insertUser(user) == 1;
    }

    private User selectUser(User user) {
        return userMapper.selectUser(user);
    }
}
