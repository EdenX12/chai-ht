package com.tian.sakura.cdd.order.task;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskManage;
import com.tian.sakura.cdd.order.context.TaskPayCallBackContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务金订单操作状态
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class TaskOrderOptService {
    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private UserTaskManage userTaskManage;
    @Autowired
    private UserTaskLineManage userTaskLineManage;
    @Autowired
    private TaskLineManage taskLineManage;

    public void changePaymentLife(TaskPayCallBackContext context) {
        String taskId = context.getUserTaskId();

        // s_user_pay
        UserPay userPay = userPayManage.selectByRelationId(taskId);
        if (userPay != null) {
            UserPay updUserPay = new UserPay();
            updUserPay.setPayStatus(context.getPayStatus().getCode());
            updUserPay.setPayType(context.getPayType());
            updUserPay.setPayTime(context.getPayTime());
            updUserPay.setId(userPay.getId());
            userPayManage.updateByPrimaryKeySelective(updUserPay);
            logger.info("用户支付记录[id={}]支付状态修改[{}]", userPay.getId(), context.getPayStatus());

        }

        //更新user task表记录
        UserTask userTask = new UserTask();
        userTask.setId(taskId);
        userTask.setPayTime(context.getPayTime());
        userTask.setPayStatus(context.getPayStatus().getCode());
        userTask.setRemark(context.getRemark());
        userTaskManage.updateByPrimaryKeySelective(userTask);
        logger.info("用户任务记录[taskId={}]支付状态修改[{}]", taskId, context.getPayStatus());

        //根据user_task_id， 查询user_task_line 数据
        List<UserTaskLine> taskLineList = userTaskLineManage.selectByTaskId(taskId);
        logger.info("用户任务[taskId={}]下共有[{}]条任务线", taskId, taskLineList.size());
        //抽取产品线id
        List<String> taskLineIds = new ArrayList<>();
        taskLineList.forEach(item -> taskLineIds.add(item.getTaskLineId()));
        if (taskLineIds.isEmpty()) {
            return;
        }

        UserTaskLine userTaskLine = new UserTaskLine();
        userTaskLine.setPayStatus(context.getPayStatus().getCode());
        userTaskLine.setPayTime(context.getPayTime());
        userTaskLine.setTaskId(taskId);
        userTaskLineManage.batchUpdateByTaskId(userTaskLine);
        logger.info("用户任务[taskId={}]下的任务线支付状态批量修改[{}]",
                taskId, context.getPayStatus());

        //用户所在的产品线任务锁定 -1
        if (EPayStatus.PAYED.equals(context.getPayStatus())) {
            taskLineManage.unLockTaskByIds(taskLineIds);
            logger.info("用户任务[taskId={}]下的购买的每条产品线的领取总数+1", taskId);
        } else {
            taskLineManage.cancelLockTaskByIds(taskLineIds);
            logger.info("用户任务[taskId={}]下的购买的每条产品线的锁定总数还原", taskId);
        }
    }
}
