package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.user.AdminUserWithdraw;
import com.tian.sakura.cdd.db.dao.user.UserWithdrawMapper;
import com.tian.sakura.cdd.db.domain.user.UserWithdraw;
import com.tian.sakura.cdd.db.manage.user.vo.WithdrawQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserWithdrawManage extends AbstractSingleManage<UserWithdraw, String> {

    @Autowired
    private UserWithdrawMapper withdrawMapper;

    @Override
    protected AbstractSingleMapper<UserWithdraw, String> getSingleMapper() {
        return withdrawMapper;
    }

    public List<UserWithdraw> selectByPage(WithdrawQueryVo queryVo) {
        return withdrawMapper.selectByPage(queryVo);
    }

    public List<UserWithdraw> queryUserWithdraw(AdminUserWithdraw adminUserWithdraw) {
        return withdrawMapper.queryUserWithdraw(adminUserWithdraw);
    }

    public BigDecimal sumUserWithdraw(AdminUserWithdraw adminUserWithdraw) {
        return withdrawMapper.sumUserWithdraw(adminUserWithdraw);
    }

    public void updateUserWithdrawStatus(UserWithdraw userWithdraw) {
        withdrawMapper.updateUserWithdrawStatus(userWithdraw.getId(), userWithdraw.getStatus());
    }
}
