package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserSign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSignMapper extends AbstractSingleMapper<UserSign, String> {

    UserSign selectByUserIdAndSignDate(@Param("userId") String userId, @Param("signDate") String signDate);

    List<UserSign> selectByUserIdBetweenSignDate(@Param("userId")String userId,
                                             @Param("startDate")String startDate,@Param("endDate") String endDate);
}