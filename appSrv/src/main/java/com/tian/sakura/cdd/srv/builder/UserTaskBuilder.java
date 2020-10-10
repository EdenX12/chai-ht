package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.common.dict.EDataChannel;
import com.tian.sakura.cdd.common.dict.EPayBizType;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.ETaskLineStatus;
import com.tian.sakura.cdd.common.entity.User;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateReq;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author lvzonggang
 */
public class UserTaskBuilder {

    public static UserTask buildNewUserTask(String userId, TaskOrderCreateReq req) {
        UserTask userTask = new UserTask();
        userTask.setId(IdGenUtil.uuid());
        userTask.setUserId(userId);
        userTask.setProductId(req.getBody().getProductId());
        userTask.setChannel(req.getHead() != null ? req.getHead().getChannel() : EDataChannel.APP.getCode());
        userTask.setPayAmount(req.getBody().getPayAmount());
        userTask.setPayStatus(EPayStatus.TO_BE_PAY.getCode());
        userTask.setTaskNumber(req.getBody().getTaskCount());
        userTask.setOrderSn(IdGenUtil.generateId());
        userTask.setUserCouponId(req.getBody().getUserCouponId());
        return userTask;
    }

    public static List<UserTaskLine> buildNewUserTaskLine(String userId, TaskOrderCreateReq req, String userTaskId, List<String> taskLineIds) {
        List<UserTaskLine> result = new ArrayList<>();
        for (String taskLineId : taskLineIds) {
            UserTaskLine userTaskLine = new UserTaskLine();
            userTaskLine.setId(IdGenUtil.uuid());
            userTaskLine.setUserId(userId);
            userTaskLine.setTaskId(userTaskId);
            userTaskLine.setProductId(req.getBody().getProductId());
            userTaskLine.setTaskLineId(taskLineId);
            // 每条线的任务金 金额
            BigDecimal payAmount = req.getBody().getTaskTotalAmount()
                    .divide(BigDecimal.valueOf(req.getBody().getTaskCount()), 2, RoundingMode.HALF_UP);
            userTaskLine.setPayAmount(payAmount);
            userTaskLine.setPayStatus(EPayStatus.TO_BE_PAY.getCode());
            userTaskLine.setLineStatus(ETaskLineStatus.RECEIVE_TASK.getCode());

            result.add(userTaskLine);
        }
        return result;
    }

    public static UserPay buildNewUserPay(String userId, String userTaskId, TaskOrderCreateReq req) {
        UserPay userPay = new UserPay();
        userPay.setId(IdGenUtil.uuid());
        userPay.setPaySn(IdGenUtil.generateId());
        userPay.setUserId(userId);
        userPay.setRelationId(userTaskId);
        userPay.setTotalAmount(req.getBody().getTaskTotalAmount());
        userPay.setPayAmount(req.getBody().getPayAmount());
        userPay.setUserCouponId(req.getBody().getUserCouponId());
        userPay.setPayType(EPayBizType.PAY_TASK.getCode()); //1 领取任务支付 2 购买订单支付
        userPay.setPayStatus(EPayStatus.TO_BE_PAY.getCode());
        userPay.setCreateTime(new Date());

        return userPay;
    }
}
