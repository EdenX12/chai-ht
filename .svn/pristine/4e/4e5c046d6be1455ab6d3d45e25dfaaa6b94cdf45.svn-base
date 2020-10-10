package com.tian.sakura.cdd.srv.service.pay;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.ETaskLineStatus;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务金支付回调接口
 * 支付回调成功
 * 修改s_user_task（1条） 及 s_user_task_line（N条）
 * 支付状态（已支付） 支付时间（sysdate）
 * s_user_line各任务线 锁定任务-1 领取任务+1
 *
 * @author lvzonggang
 */
@Service
public class TaskPayCallBack {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserTaskManage userTaskManage;
    @Autowired
    private UserTaskLineManage userTaskLineManage;
    @Autowired
    private TaskLineManage taskLineManage;
    @Autowired
    private UserPayManage userPayManage;

    /**
     * 支付成功
     *
     * @param context 上下文
     */
    public void payOk(TaskPayCallBackContext context) {
        doPay(context);
    }

    /**
     * 用户取消支付 或者订单过期
     *
     * @param context 上下文
     */
    public void payFail(TaskPayCallBackContext context) {
        doPay(context);
    }

    private void doPay(TaskPayCallBackContext context) {
        String taskId = context.getUserTaskId();
        // userPay
        UserPay updUserPay = new UserPay();
        updUserPay.setPayStatus(context.getPayStatus().getCode());
        //updUserPay.setPayType(1);
        updUserPay.setPayTime(new Date());
        updUserPay.setId(context.getUserPayId());
        userPayManage.updateByPrimaryKeySelective(updUserPay);
        logger.info("用户支付记录[id={}]支付状态修改[{}]", context.getUserPayId(), context.getPayStatus());

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
            // 更新状态- 拆满
            for (String taskLineId : taskLineIds) {
                TaskLine taskLine = taskLineManage.selectByPrimary(taskLineId);
                if (taskLine.getTotalTask() == taskLine.getReceivedTask()) {
                    TaskLine updLine = new TaskLine();
                    updLine.setId(taskLineId);
                    taskLine.setLineStatus(1); // 拆满
                    taskLineManage.updateByPrimaryKeySelective(updLine);
                }
            }

            logger.info("用户任务[taskId={}]下的购买的每条产品线的领取总数+1", taskId);
        } else {
            taskLineManage.cancelLockTaskByIds(taskLineIds);
            // 更新状态- 拆满
            for (String taskLineId : taskLineIds) {
                TaskLine taskLine = taskLineManage.selectByPrimary(taskLineId);
                if (taskLine.getTotalTask() != taskLine.getReceivedTask()) {
                    TaskLine updLine = new TaskLine();
                    updLine.setId(taskLineId);
                    taskLine.setLineStatus(0); // 拆满
                    taskLineManage.updateByPrimaryKeySelective(updLine);
                }
            }
            logger.info("用户任务[taskId={}]下的购买的每条产品线的锁定总数还原", taskId);
        }
    }


}
