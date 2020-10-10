package com.tian.sakura.cdd.batch.service;

import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskManage;
import com.tian.sakura.cdd.order.task.TaskOrderPaymentLifeCycle;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.tian.sakura.cdd.common.constants.SrvConstants.PARAM_KEY_PRD_ORD_PAYTIME;
import static com.tian.sakura.cdd.common.constants.SrvConstants.PARAM_KEY_TASK_ORD_PAYTIME;

/**
 * 针对任务金超时并且未支付的订单，执行关闭操作
 *
 * @author lvzonggang
 */

@Service
public class TaskOrderCloseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskOrderPaymentLifeCycle orderPaymentLifeCycle;
    @Autowired
    private ParamsManage paramsManage;
    @Autowired
    private UserTaskManage userTaskManage;

    /**
     * 没5分钟扫描一次未支付的任务金订单
     *
     */
    public void closeTaskOrder() {
        // 查询超过系统配置的未支付时间的订单，关闭订单。
        String payTimeMinutesValue = paramsManage.getValueByKey(PARAM_KEY_TASK_ORD_PAYTIME);
        Integer payTimeMinutes = Integer.valueOf(payTimeMinutesValue);

        DateTime now = DateTime.now();
        //最后支付时间 多计算了三分钟
        Date endPayTime = now.minusMinutes(payTimeMinutes + 3).toDate();

        List<UserTask> unPayOrderList = userTaskManage.selectUnPayOrder(endPayTime);
        logger.info("本次需要关闭的任务金订单数量[{}]", unPayOrderList.size());

        for (UserTask userTask : unPayOrderList) {
            try {
                orderPaymentLifeCycle.closeOrder(userTask.getId());
            } catch (Exception e) {
                logger.error("任务金订单[userTaskId={}]关闭异常", userTask.getId());
                logger.error(e.getMessage(),e);
            }

        }


    }
}
