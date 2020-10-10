package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.dict.EFollowStatus;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserFollowMapper;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户关注表数据访问服务
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserFollowManage extends AbstractSingleManage<UserFollow, String> {

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    protected AbstractSingleMapper<UserFollow, String> getSingleMapper() {
        return userFollowMapper;
    }

    public UserFollow selectByUserIdAndProductId(String userId, String productId) {
        return userFollowMapper.selectByUserIdAndProductId(userId, productId);
    }

    public boolean checkFollow(SUser user, String productId) {
        if (user == null) {
            return false;
        }
        // 查询是否关注
        UserFollow userFollow = userFollowMapper.selectByUserIdAndProductId(user.getId(), productId);
        if (userFollow == null) {
            return false;
        } else {
            return userFollow.getFollowStatus() == EFollowStatus.Y.getCode();
        }
    }
}
