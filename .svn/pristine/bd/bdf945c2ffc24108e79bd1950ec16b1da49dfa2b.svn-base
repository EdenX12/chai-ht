package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.order.AdminUserTaskReq;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.manage.user.vo.UserTaskQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserTaskMapper extends AbstractSingleMapper<UserTask, String> {

    List<UserTask> selectByQueryVo(UserTaskQueryVo queryVo);

    List<UserTask> selectUnPayOrder(@Param("endPayTime") Date endPayTime);

    List<UserTask> listTaskOrder(AdminUserTaskReq userTask);
}