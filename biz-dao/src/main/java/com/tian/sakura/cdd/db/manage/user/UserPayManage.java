package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserPayMapper;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserPayManage extends AbstractSingleManage<UserPay, String> {

    @Autowired
    private UserPayMapper userPayMapper;

    @Override
    protected AbstractSingleMapper<UserPay, String> getSingleMapper() {
        return userPayMapper;
    }

    public UserPay selectByUserIdAndBizId(String userId, String bizId) {
        return userPayMapper.selectByUserIdAndBizId(userId, bizId);
    }

    // 根据关联id查询支付记录
    public UserPay selectByRelationId(String relationId) {
        return userPayMapper.selectByUserIdAndBizId(null, relationId);
    }

    public UserPay selectByPaySn(String paySn) {
        return userPayMapper.selectByPaySn(paySn);
    }
}
