package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.order.AdminUserTaskReq;
import com.tian.sakura.cdd.db.dao.user.UserTaskMapper;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.manage.user.vo.UserTaskQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户task数据访问服务
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserTaskManage extends AbstractSingleManage<UserTask, String> {

    @Autowired
    private UserTaskMapper userTaskMapper;

    protected AbstractSingleMapper<UserTask, String> getSingleMapper() {
        return userTaskMapper;
    }

    /**
     * 查询用户待支付任务金订单
     * @return
     */
    public List<UserTask> selectTaskOrderForPayByUserId(String userId) {
        UserTaskQueryVo queryVo = new UserTaskQueryVo();
        queryVo.setUserId(userId);
        queryVo.setPayStatus(EPayStatus.TO_BE_PAY.getCode());
        return userTaskMapper.selectByQueryVo(queryVo);
    }

    public List<UserTask> selectUnPayOrder(Date endPayTime) {
        return userTaskMapper.selectUnPayOrder(endPayTime);
    }

    public List<UserTask> listTaskOrder(AdminUserTaskReq userTask) {
        return userTaskMapper.listTaskOrder(userTask);
    }
}
