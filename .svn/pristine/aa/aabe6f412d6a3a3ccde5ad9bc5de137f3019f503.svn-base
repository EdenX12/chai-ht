package com.tian.sakura.cdd.order.profit;

import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserRelationManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class TaskLineSearchService {
    @Autowired
    private UserTaskLineManage userTaskLineManage;
    @Autowired
    private TaskLineManage taskLineManage;
    @Autowired
    private UserRelationManage userRelationManage;

    /**
     * 支付商品后，查找结算佣金的任务线
     *
     * @param rewardSettelParam 结算参数
     *
     * @return
     */
    public List<String> searchTaskLine(RewardSettelParam rewardSettelParam) {
        String productId = rewardSettelParam.getProductId();
        int prdCnt = rewardSettelParam.getPrdCount();
        String userId = rewardSettelParam.getBuyerId();

        int taskLineCntEnable = prdCnt;
        // 查找当前购买者是否是产品的拆家
        List<UserTaskLine> userTaskLines = userTaskLineManage.getTaskLine(userId, productId);
        List<String> taskLineIds = new ArrayList<>();
        // 任务线取购买者的
        for (int i=0; i<prdCnt; i++) {
            taskLineIds.add(userTaskLines.get(i).getTaskLineId());
            taskLineCntEnable --;
            if (taskLineCntEnable == 0) {
                break;
            }
        }

        // 购买数量大于拆家任务线的数量，
        if (taskLineCntEnable > 0) {
            // 是否有上级
            UserRelation parentUser = userRelationManage.selectGuardParentIdByUserId(userId);
            if (parentUser != null) {
                // 继续寻找上级的购买任务线
                List<UserTaskLine> parentUserTaskLines = userTaskLineManage.getTaskLine(parentUser.getParentId(), productId);

                for (UserTaskLine taskLine : parentUserTaskLines) {
                    // 如果包含了当前的任务线，继续查找任务线
                    String taskLineId = taskLine.getTaskLineId();
                    if (taskLineIds.contains(taskLineId)) {
                        continue;
                    } else {
                        taskLineIds.add(taskLineId);
                        taskLineCntEnable --;
                    }
                    if (taskLineCntEnable == 0) {
                        break;
                    }
                }
            }
        }
        // 寻找产品的任务线，产生时间 从前到后
        if (taskLineCntEnable > 0) {
            List<TaskLine> taskLineList = taskLineManage.getSettleTaskLineByProductId(productId,taskLineIds,taskLineCntEnable);

            if (!taskLineList.isEmpty()) {
                taskLineList.forEach(item -> {
                    taskLineIds.add(item.getId());
                });

            }

        }

        return taskLineIds;
    }
}
