package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserWechat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserWechatMapper extends AbstractSingleMapper<UserWechat, String> {

    UserWechat selectByOpenidAndUnionid(@Param("openId") String openid, @Param("unionId")String unionid);

    List<UserWechat> selectByUnionid(@Param("unionId")String unionid);
}