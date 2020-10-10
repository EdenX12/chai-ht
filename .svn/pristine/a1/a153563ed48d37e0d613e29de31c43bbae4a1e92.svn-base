package com.tian.sakura.cdd.db.manage.task;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.task.UserShareMapper;
import com.tian.sakura.cdd.db.domain.task.UserShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户分享数据访问服务
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserShareManage extends AbstractSingleManage<UserShare, String> {

    @Autowired
    private UserShareMapper userShareMapper;

    @Override
    protected AbstractSingleMapper<UserShare, String> getSingleMapper() {
        return userShareMapper;
    }

    public UserShare selectByUserIdAndProductId(String userId, String productId) {
        return userShareMapper.selectByUserIdAndProductId(userId, productId);
    }
}
