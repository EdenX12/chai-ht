package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserLevelMapper;
import com.tian.sakura.cdd.db.domain.user.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLevelManage extends AbstractSingleManage<UserLevel, String> {

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Override
    protected AbstractSingleMapper getSingleMapper() {
        return userLevelMapper;
    }

    public List<UserLevel> getUserLevelList(UserLevel userLevel) {
        return userLevelMapper.getUserLevelList(userLevel);
    }
}
