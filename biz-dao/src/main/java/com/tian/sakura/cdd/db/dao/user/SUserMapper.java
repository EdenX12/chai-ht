package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.user.AdminUserReq;
import com.tian.sakura.cdd.db.domain.user.SUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SUserMapper extends AbstractSingleMapper<SUser, String> {

    SUser getUserByPhone(@Param("phone") String phone);

    SUser getUserByUnionId(@Param("unionId") String unionId);

    SUser getUserByInviteCode(@Param("inviteCode") String inviteCode);

    List<SUser> listUser(AdminUserReq adminUserReq);

    int updateTotalAmountById(@Param("userId") String userId, @Param("amount") BigDecimal amount);
}