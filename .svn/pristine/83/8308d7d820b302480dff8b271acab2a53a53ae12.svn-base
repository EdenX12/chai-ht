package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.user.AdminUserWithdraw;
import com.tian.sakura.cdd.db.domain.user.UserWithdraw;
import com.tian.sakura.cdd.db.manage.user.vo.WithdrawQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserWithdrawMapper extends AbstractSingleMapper<UserWithdraw, String> {

    List<UserWithdraw> selectByPage(WithdrawQueryVo queryVo);

    List<UserWithdraw> queryUserWithdraw(AdminUserWithdraw adminUserWithdraw);

    BigDecimal sumUserWithdraw(AdminUserWithdraw adminUserWithdraw);

    int updateUserWithdrawStatus(@Param("id") String id, @Param("status") String status);
}