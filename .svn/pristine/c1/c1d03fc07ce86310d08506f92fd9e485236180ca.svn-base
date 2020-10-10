package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserWechatMapper;
import com.tian.sakura.cdd.db.domain.user.UserWechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 微信用户信息数据访问服务
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserWechatManage extends AbstractSingleManage<UserWechat, String> {

    @Autowired
    private UserWechatMapper userWechatMapper;

    @Override
    protected AbstractSingleMapper<UserWechat, String> getSingleMapper() {
        return userWechatMapper;
    }

    public UserWechat selectByOpenidAndUnionid(String openid, String unionid) {
        return userWechatMapper.selectByOpenidAndUnionid(openid, unionid);
    }

    public List<UserWechat> selectByUnionid(String unionid) {
        return userWechatMapper.selectByUnionid(unionid);
    }
}
