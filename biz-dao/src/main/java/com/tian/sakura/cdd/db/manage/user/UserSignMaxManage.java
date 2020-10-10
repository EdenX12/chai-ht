package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserSignMapper;
import com.tian.sakura.cdd.db.dao.user.UserSignMaxMapper;
import com.tian.sakura.cdd.db.domain.user.UserSign;
import com.tian.sakura.cdd.db.domain.user.UserSignMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserSignMaxManage extends AbstractSingleManage<UserSignMax, String> {

    @Autowired
    private UserSignMaxMapper userSignMaxMapper;

    @Override
    protected AbstractSingleMapper<UserSignMax, String> getSingleMapper() {
        return userSignMaxMapper;
    }

    
}
