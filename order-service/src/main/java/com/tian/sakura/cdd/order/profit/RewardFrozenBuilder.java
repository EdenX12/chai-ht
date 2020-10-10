package com.tian.sakura.cdd.order.profit;

import com.tian.sakura.cdd.common.dict.EBonusType;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.domain.userBonusLog.UserBonusLog;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.log.UserBonusLogManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.user.UserRelationManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.tian.sakura.cdd.common.constants.SrvConstants.*;
import static com.tian.sakura.cdd.common.constants.SrvConstants.REWARD_RATIO_VER2;
import static com.tian.sakura.cdd.common.constants.SrvConstants.REWARD_RATIO_VER3;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class RewardFrozenBuilder {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductManage productManage;
    @Autowired
    private UserBonusLogManage bonusLogManage;
    @Autowired
    private UserTaskLineManage userTaskLineManage;
    @Autowired
    private UserRelationManage userRelationManage;

    /**
     * 购买商品后，结算佣金（冻结状态）
     *
     */
    public List<UserBonusLog>  buildBonusFromTaskLine(RewardSettelParam rewardSettelParam, String taskLineId) {
        List<UserBonusLog> bonusLogList = new ArrayList<>();

        String buyerId = rewardSettelParam.getBuyerId();
        String orderDetailId = rewardSettelParam.getOrderDetailId();

        // 商品分润费用设置
        String produtId = rewardSettelParam.getProductId();
        Product product = productManage.selectByPrimary(produtId);
        // 总佣金
        BigDecimal reward = product.getTotalReward();

        // 计算该条任务线的费用
        // a. 购买返现 总佣金20%
        BigDecimal rtnReward = reward.multiply(new BigDecimal(REWARD_RATIO_RTN));
        logger.info("购买者[{}]支付订单[{}]返现的金额[{}]", buyerId, orderDetailId, rtnReward);
        // 记录冻结记录
        doRtnBonusLog(rewardSettelParam, taskLineId, rtnReward, bonusLogList);

        // b. 任务躺赢 同组任务线上的拆家（X人）均分40%
        List<UserTaskLine> userTaskLineList = userTaskLineManage.selectByTaskLineId(taskLineId);
        int userCount = userTaskLineList.size();
        logger.info("佣金结算-任务线[{}]上的拆家人数[{}]", taskLineId, userCount);
        if (userCount > 0) {
            // 每个人的佣金
            BigDecimal taskReward = reward.multiply(new BigDecimal(REWARD_RATIO_TASK))
                    .divide(new BigDecimal(userCount),2, RoundingMode.HALF_UP);
            logger.info("佣金结算-任务线[{}]上的拆家获得的佣金[{}]", taskLineId, taskReward);
            for (UserTaskLine item : userTaskLineList) {
                doTaskBonusLog(item, rewardSettelParam, taskReward,bonusLogList);
            }
        }

        // c.横向躺赢  同组任务线上的每个拆家对应的上级（Y人，Y<=X）均分25%，
        List<String> userIds = new ArrayList<>();
        for (UserTaskLine item : userTaskLineList) {
            userIds.add(item.getUserId());
        }
        // 查询任务线的直接上级
        List<UserRelation> userRelationList =  userRelationManage.selectByUserIds(userIds);
        int parentCount = userRelationList.size();
        if (parentCount > 0) {
            // 佣金
            BigDecimal horiReward = reward.multiply(new BigDecimal(REWARD_RATIO_HORIZ));
            for (UserRelation item : userRelationList) {
                doBonusLog(item.getParentId(), rewardSettelParam, taskLineId, horiReward, EBonusType.HOR_REWARD,bonusLogList);
            }
        }
        // d. 纵向躺赢 从买家的捆绑关系朝上查，上三级3个人按（8-5-2）分15%
        BigDecimal oneParentReward = reward.multiply(new BigDecimal(REWARD_RATIO_VER1));
        BigDecimal twoParentReward = reward.multiply(new BigDecimal(REWARD_RATIO_VER2));
        BigDecimal threeParentReward = reward.multiply(new BigDecimal(REWARD_RATIO_VER3));
        // 第一级上级
        UserRelation oneParent = doParentBonusLog(buyerId, rewardSettelParam,taskLineId,oneParentReward,bonusLogList);
        if (oneParent != null) {
            // 第二级上级
            UserRelation twoParent = doParentBonusLog(oneParent.getParentId(), rewardSettelParam,taskLineId,twoParentReward,bonusLogList);
            if (twoParent != null) {
                // 第三级上级
                doParentBonusLog(twoParent.getParentId(), rewardSettelParam,taskLineId,threeParentReward,bonusLogList);
            }
        }

        return bonusLogList;
    }

    // 购买返现冻结金额
    private void doRtnBonusLog(RewardSettelParam rewardSettelParam, String taskLineId, BigDecimal bonusAmount, List<UserBonusLog> bonusLogList) {
        UserBonusLog bonusLog = new UserBonusLog();
        bonusLog.setId(IdGenUtil.uuid());
        bonusLog.setUserId(rewardSettelParam.getBuyerId());
        bonusLog.setProductId(rewardSettelParam.getProductId());
        bonusLog.setOrderDetailId(rewardSettelParam.getOrderDetailId());
        bonusLog.setUserTaskLineId("");
        bonusLog.setTaskLineId(taskLineId);
        bonusLog.setBonusAmount(bonusAmount);
        bonusLog.setBonusType(EBonusType.BUY_RTN_CASH.getCode());
        bonusLog.setLogStatus(0);

        bonusLogList.add(bonusLog);
    }

    // 任务躺赢冻结金额
    private void doTaskBonusLog(UserTaskLine userTaskLine, RewardSettelParam rewardSettelParam,
                                BigDecimal bonusAmount, List<UserBonusLog> bonusLogList) {
        UserBonusLog bonusLog = new UserBonusLog();
        bonusLog.setId(IdGenUtil.uuid());
        bonusLog.setUserId(userTaskLine.getUserId());
        bonusLog.setProductId(rewardSettelParam.getProductId());
        bonusLog.setOrderDetailId(rewardSettelParam.getOrderDetailId());
        bonusLog.setUserTaskLineId(userTaskLine.getId());
        bonusLog.setTaskLineId(userTaskLine.getTaskLineId());
        bonusLog.setBonusAmount(bonusAmount);
        bonusLog.setBonusType(EBonusType.TASK_LINE_REWARD.getCode());
        bonusLog.setLogStatus(0);

        bonusLogList.add(bonusLog);
    }
    //横向躺赢
    private void doBonusLog(String userId, RewardSettelParam rewardSettelParam, String taskLineId,
                            BigDecimal bonusAmount, EBonusType bonusType, List<UserBonusLog> bonusLogList) {
        UserBonusLog bonusLog = new UserBonusLog();
        bonusLog.setId(IdGenUtil.uuid());
        bonusLog.setUserId(userId);
        bonusLog.setProductId(rewardSettelParam.getProductId());
        bonusLog.setOrderDetailId(rewardSettelParam.getOrderDetailId());
        //bonusLog.setUserTaskLineId(userTaskLine.getId());
        bonusLog.setTaskLineId(taskLineId);
        bonusLog.setBonusAmount(bonusAmount);
        bonusLog.setBonusType(bonusType.getCode());
        bonusLog.setLogStatus(0);

        bonusLogList.add(bonusLog);
    }
    // 纵向上级躺赢
    private UserRelation doParentBonusLog(String userId,RewardSettelParam rewardSettelParam, String taskLineId,
                                          BigDecimal bonusAmount, List<UserBonusLog> bonusLogList)  {
        UserRelation parentUser = userRelationManage.selectGuardParentIdByUserId(userId);
        if (parentUser != null) {
            String parentId = parentUser.getParentId();
            doBonusLog(parentId, rewardSettelParam, taskLineId, bonusAmount, EBonusType.VER_REWARD,bonusLogList);
        }
        return parentUser;
    }
}
