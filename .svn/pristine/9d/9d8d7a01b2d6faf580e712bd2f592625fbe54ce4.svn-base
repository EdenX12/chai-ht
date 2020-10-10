package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserSignMapper;
import com.tian.sakura.cdd.db.domain.user.UserSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserSignManage extends AbstractSingleManage<UserSign, String> {

    @Autowired
    private UserSignMapper userSignMapper;

    @Override
    protected AbstractSingleMapper<UserSign, String> getSingleMapper() {
        return userSignMapper;
    }

    public UserSign selectByUserIdAndSignDate(String userId, String signDate) {
        return userSignMapper.selectByUserIdAndSignDate(userId, signDate);
    }

    public List<UserSign> selectByUserIdBetweenSignDate(String userId, String startDate, String endDate) {
        return userSignMapper.selectByUserIdBetweenSignDate(userId, startDate, endDate);
    }
}
