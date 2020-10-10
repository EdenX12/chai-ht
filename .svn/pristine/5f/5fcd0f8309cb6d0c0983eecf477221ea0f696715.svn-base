package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.user.AdminUserReq;
import com.tian.sakura.cdd.db.dao.user.SUserMapper;
import com.tian.sakura.cdd.db.domain.user.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 注册用户db操作api
 *
 * @author lvzonggang
 */
@Transactional
@Service
public class SUserManage extends AbstractSingleManage<SUser, String> {

    @Autowired
    private SUserMapper userMapper;

    @Override
    protected AbstractSingleMapper<SUser, String> getSingleMapper() {
        return userMapper;
    }

    public SUser getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    public SUser getUserByUnionId(String unionId) {
        return userMapper.getUserByUnionId(unionId);
    }

    public SUser getUserByInviteCode(String inviteCode) {
        return userMapper.getUserByInviteCode(inviteCode);
    }

    public List<SUser> listUser(AdminUserReq adminUserReq) {
        return userMapper.listUser(adminUserReq);
    }

    public void updateTotalAmountById(String userId, BigDecimal amount) {
         userMapper.updateTotalAmountById(userId,amount);
    }
}
