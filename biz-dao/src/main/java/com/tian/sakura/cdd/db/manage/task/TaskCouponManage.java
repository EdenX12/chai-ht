package com.tian.sakura.cdd.db.manage.task;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.task.TaskCouponMapper;
import com.tian.sakura.cdd.db.domain.task.TaskCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCouponManage extends AbstractSingleManage<TaskCoupon, String> {

    @Autowired
    private TaskCouponMapper taskCouponMapper;

    @Override
    protected AbstractSingleMapper<TaskCoupon, String> getSingleMapper() {
        return taskCouponMapper;
    }
}
