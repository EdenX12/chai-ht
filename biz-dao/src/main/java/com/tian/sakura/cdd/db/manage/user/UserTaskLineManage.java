package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.userTaskLine.UserTaskLineMapper;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserTaskLineManage extends AbstractSingleManage<UserTaskLine, String> {

    @Autowired
    private UserTaskLineMapper userTaskLineMapper;

    @Override
    protected AbstractSingleMapper<UserTaskLine, String> getSingleMapper() {
        return userTaskLineMapper;
    }

    // 查询用户正在进行中的任务
    public List<UserTaskLine> getTaskLine(String userId, String productId) {
        return userTaskLineMapper.getTaskLine(userId, productId);
    }

    public List<UserTaskLine> selectCntByUserIdGroupByPrdId(String userId) {
        return userTaskLineMapper.selectCntByUserIdGroupByPrdId(userId);
    }

    //可分配的任务线
    public List<UserTaskLine> selectForDispatch(String userId, String productId) {
        return userTaskLineMapper.selectForDispatch(userId, productId);
    }

    public int batchUpdateByTaskId(UserTaskLine userTaskLine) {
        return userTaskLineMapper.batchUpdateByTaskId(userTaskLine);
    }

    public List<UserTaskLine> selectByTaskId(String taskId) {
        return userTaskLineMapper.selectByTaskId(taskId);
    }

    // 查询商品任务线上的拆家任务
    public List<UserTaskLine> selectByTaskLineId(String taskLineId) {
       return  userTaskLineMapper.qryByTaskLineId(taskLineId);
    }
}
