package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserFollow;
import org.apache.ibatis.annotations.Param;

public interface UserFollowMapper extends AbstractSingleMapper<UserFollow, String> {

    UserFollow selectByUserIdAndProductId(@Param("userId") String userId, @Param("productId") String productId);

}