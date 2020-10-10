package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserBankMapper;
import com.tian.sakura.cdd.db.domain.user.UserBank;
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
public class UserBankManage extends AbstractSingleManage<UserBank, String> {

    @Autowired
    private UserBankMapper userBankMapper;

    @Override
    protected AbstractSingleMapper<UserBank, String> getSingleMapper() {
        return userBankMapper;
    }

    public List<UserBank> selectByUserId(String userId) {
        return userBankMapper.selectByUserId(userId);
    }
}
