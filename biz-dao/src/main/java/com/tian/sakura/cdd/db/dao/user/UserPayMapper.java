package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import org.apache.ibatis.annotations.Param;

public interface UserPayMapper extends AbstractSingleMapper<UserPay, String> {

    UserPay selectByUserIdAndBizId(@Param("userId") String userId,@Param("relationId") String bizId);

    UserPay selectByPaySn(@Param("paySn")String paySn);
}