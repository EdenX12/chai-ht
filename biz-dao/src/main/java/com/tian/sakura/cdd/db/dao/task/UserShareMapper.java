package com.tian.sakura.cdd.db.dao.task;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.task.UserShare;
import org.apache.ibatis.annotations.Param;

public interface UserShareMapper extends AbstractSingleMapper<UserShare, String> {

    UserShare selectByUserIdAndProductId(@Param("userId") String userId, @Param("productId") String productId);
}