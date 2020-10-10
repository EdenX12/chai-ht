package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserLoginLogMapper;
import com.tian.sakura.cdd.db.domain.user.UserLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户登录日志  数据访问类
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserLoginLogManage extends AbstractSingleManage<UserLoginLog, String> {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    protected AbstractSingleMapper<UserLoginLog, String> getSingleMapper() {
        return userLoginLogMapper;
    }
}
