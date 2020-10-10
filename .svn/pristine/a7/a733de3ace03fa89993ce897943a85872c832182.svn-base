package com.tian.sakura.cdd.order.profit;

import com.tian.sakura.cdd.common.dict.EBonusType;
import com.tian.sakura.cdd.common.dict.ESettleStatus;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.domain.userBonusLog.UserBonusLog;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.log.UserBonusLogManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserRelationManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import com.tian.sakura.cdd.order.acct.UserAcctParameter;
import com.tian.sakura.cdd.order.acct.UserAcctService;
import javafx.util.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.log.LogInputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tian.sakura.cdd.common.constants.SrvConstants.*;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class ProfitCalculateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskLineManage taskLineManage;
    @Autowired
    private TaskLineSearchService taskLineSearchService;
    @Autowired
    private RewardFrozenBuilder rewardFrozenBuilder;

    @Autowired
    private UserBonusLogManage bonusLogManage;
    @Autowired
    private UserAcctService userAcctService;


    /**
     * 购买商品后，结算佣金（冻结状态）
     *
     */
    public void frozeReward(RewardSettelParam rewardSettelParam)  {
        // 查找任务线
        List<String> taskLineList = taskLineSearchService.searchTaskLine(rewardSettelParam);
        // 任务线- 结算中
        if (taskLineList.isEmpty()) {
            return;
        }
        for (String taskLineId : taskLineList) {
            TaskLine taskLine = new TaskLine();
            taskLine.setId(taskLineId);
            taskLine.setSettleStatus(ESettleStatus.SETTLING.getCode());
            taskLine.setOrderProductId(rewardSettelParam.getOrderDetailId());
            taskLineManage.updateByPrimaryKeySelective(taskLine);
        }
        //taskLineManage.updateSettleStatusByOrderId(rewardSettelParam.getOrderDetailId(), ESettleStatus.SETTLING);


        List<UserBonusLog> bonusLogList = new ArrayList<>();
        for (String taskLineId : taskLineList) {
            bonusLogList.addAll(rewardFrozenBuilder.buildBonusFromTaskLine(rewardSettelParam, taskLineId));
        }

        // 持久化数据 冻结数据
        bonusLogManage.batchInsert(bonusLogList);


        // map<String,Bigdecimal>  key=userId, value=frozenAmt
        Map<String, BigDecimal> frozenAmtMp = new HashMap<>();
        bonusLogList.forEach(item ->{
            String key = item.getUserId();
            if (frozenAmtMp.containsKey(key)) {
                BigDecimal frozenAmt = frozenAmtMp.get(key);
                frozenAmtMp.put(key,frozenAmt.add(item.getBonusAmount()));
            } else {
                frozenAmtMp.put(key,item.getBonusAmount());
            }
        });
        // 用户账户冻结金额变化
        for (Map.Entry<String, BigDecimal> entry : frozenAmtMp.entrySet()) {
            UserAcctParameter acctParameter = UserAcctParameter
                    .builder()
                    .userId(entry.getKey())
                    .changeLockAmt(entry.getValue())
                    .build();
            userAcctService.changeUserAcct(acctParameter);
        }

    }


    public void settleReward(RewardSettelParam rewardSettelParam) {
        String orderDetailId = rewardSettelParam.getOrderDetailId();
        logger.info("开始结算订单[{}]锁冻结的任务金，从冻结金额转向可用金额", orderDetailId);

        //
        // 任务线- 已分润
        taskLineManage.updateSettleStatusByOrderId(rewardSettelParam.getOrderDetailId(), ESettleStatus.SETTLED);

        // 冻结金额
        // 查询 s_bonus_log   order_detail_id  更新状态  1-结算完成
        bonusLogManage.updateStatusByDetailIdForSettle(rewardSettelParam.getOrderDetailId(), 1);
        List<UserBonusLog> bonusLogList = bonusLogManage.selectByOrderDetailId(rewardSettelParam.getOrderDetailId());

        UserBonusLog updBonusLog = new UserBonusLog();
        updBonusLog.setOrderDetailId("");


        // 统计每个人的冻结金额
        Map<String, BigDecimal> userAmountMap = new HashMap<>();
        for (UserBonusLog bonusLog : bonusLogList) {
            String userId = bonusLog.getUserId();
            if (userAmountMap.containsKey(userId)) {
                BigDecimal amount = bonusLog.getBonusAmount();
                userAmountMap.put(userId, bonusLog.getBonusAmount().add(amount));
            } else {
                userAmountMap.put(userId, bonusLog.getBonusAmount());
            }
        }

        // 释放冻结金额 s_user  total_amount lock_amount
        for (Map.Entry<String, BigDecimal> entry : userAmountMap.entrySet()) {
            UserAcctParameter acctParameter = UserAcctParameter
                    .builder()
                    .userId(entry.getKey())
                    .changeAmt(entry.getValue())
                    .changeLockAmt(entry.getValue().negate())
                    .build();
            userAcctService.changeUserAcct(acctParameter);
        }
    }
}
