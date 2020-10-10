package com.tian.sakura.cdd.order.task;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.order.context.TaskPayCallBackContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
public class TaskOrderPaymentLifeCycle {

    @Autowired
    private TaskOrderOptService taskOrderOptService;

    public void createOrder() {

    }

    public void cancelOrder() {

    }

    public void closeOrder(String userTaskId) {
        TaskPayCallBackContext context = new TaskPayCallBackContext();
        context.setUserTaskId(userTaskId);
        context.setPayStatus(EPayStatus.CLOSED);
        context.setPayTime(new Date());
        context.setRemark("订单超时关闭");

        taskOrderOptService.changePaymentLife(context);
    }

    public void payOrder() {

    }
}
