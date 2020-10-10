package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.common.dict.EAmtChangeType;
import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.ERelationType;
import com.tian.sakura.cdd.common.entity.KeyValuePair;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.log.UserAmountLogManage;
import com.tian.sakura.cdd.db.manage.mytask.MyTaskManage;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserCouponManage;
import com.tian.sakura.cdd.db.manage.user.UserRelationManage;
import com.tian.sakura.cdd.srv.builder.LoginBuilder;
import com.tian.sakura.cdd.srv.web.login.dto.LoginRsp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class UserInfoDecorator {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MyTaskManage myTaskManage;
    @Autowired
    private UserCouponManage userCouponManage;
    @Autowired
    private SUserManage userManage;
    @Autowired
    private UserRelationManage userRelationManage;

    @Autowired
    private OrderDetailManage orderDetailManage;
    @Autowired
    private UserAmountLogManage userAmountLogManage;

    public void decorate(SUser loginUser, LoginRsp loginRsp) {

        //上级邀请码
        String parentId = loginUser.getParentId();
        if (StringUtils.isNotEmpty(parentId)) {
            SUser parentuser = userManage.selectByPrimary(parentId);
            if (parentuser != null) {
                loginRsp.setParentInviteCode(parentuser.getInviteCode());
            }
        }

        Integer beanOfHolder = loginUser.getRewardBean();
        if (beanOfHolder == null) {
            beanOfHolder = 0;
        }
        loginRsp.setRewardBean(beanOfHolder);
        logger.info("用户[{}]持有的猎豆-{}", loginUser.getUserPhone(), beanOfHolder);
        //任务限额-当前领取的任务
        int taskCntOfReceived = myTaskManage.totalTaskLineByUserId(loginUser.getId());
        logger.info("用户[{}]已参与的任务数量-{}", loginUser.getUserPhone(), taskCntOfReceived);
        loginRsp.setReceiveTask(taskCntOfReceived);

        //任务限额-上限
        int taskLimit = LoginBuilder.instance().getTaskLimit(beanOfHolder, taskCntOfReceived > 0);
        logger.info("用户[{}]任务限额数量-{}", loginUser.getUserPhone(), taskLimit);
        loginRsp.setTaskLimit(taskLimit);

        //级别名称
        String levelName = LoginBuilder.instance().getLevelName(beanOfHolder, taskCntOfReceived > 0);
        loginRsp.setLevelName(levelName);

        //抵用券数量
        int couponCntOfHolder = userCouponManage.selectCntByUserId(loginUser.getId());
        logger.info("用户[{}]优惠券数量-{}", loginUser.getUserPhone(), couponCntOfHolder);
        loginRsp.setCouponCount(couponCntOfHolder);

        //订单数据汇总
        List<KeyValuePair> keyValuePairs = orderDetailManage.selectCntOfOrderStatusByUserId(loginUser.getId());
        keyValuePairs.forEach(item -> {
            Integer orderStatus = Integer.valueOf(item.getName());
            Integer cnt = Integer.valueOf(item.getValue());
            if (orderStatus == EOrderStatus.TO_BE_PAY.getCode()) {
                loginRsp.setCntOfToBePay(cnt);
                logger.info("用户[{}]待付款订单数量-{}", loginUser.getUserPhone(), cnt);
            } else if (orderStatus == EOrderStatus.TO_BE_SEND.getCode()) {
                loginRsp.setCntOfToBeSend(cnt);
                logger.info("用户[{}]待发货订单数量-{}", loginUser.getUserPhone(), cnt);
            } else if (orderStatus == EOrderStatus.SENDED.getCode()) {
                loginRsp.setCntOfToBeReceive(cnt);
                logger.info("用户[{}]待收货订单数量-{}", loginUser.getUserPhone(), cnt);
            } else if (orderStatus == EOrderStatus.APPLY_BACK.getCode()) {
                loginRsp.setCntOfBack(cnt);
                logger.info("用户[{}]退换货订单数量-{}", loginUser.getUserPhone(), cnt);
            }
        });

        //我的战队 TODO
        //战队人数
        Integer teamCnt = userRelationManage.selectCntByUserIdAndRelationType(
                loginUser.getId(), ERelationType.GUARD.getCode());
        loginRsp.setTeamPersonCnt(teamCnt);
        //今日新增
        Integer cntOfToday = userRelationManage.selectCntByUserIdAndRelationType(
                loginUser.getId(), ERelationType.GUARD.getCode());
        loginRsp.setTeamPersonCntOfToday(cntOfToday);

        //战队累计收益
        List<UserAmountLog> amountLogList = userAmountLogManage.selectTotalRewardByUserId(loginUser.getId());
        BigDecimal totalReward = BigDecimal.ZERO;
        for (UserAmountLog item : amountLogList) {
            if (item.getChangeType() == EAmtChangeType.HOR_REWARD.getCode()
                    || item.getChangeType() == EAmtChangeType.VER_REWARD.getCode()) {
                totalReward = totalReward.add(item.getChangeAmount());
            }
        }
        loginRsp.setTeamTotalReward(totalReward);
        //今日新增
        List<UserAmountLog> todayAmountLogList = userAmountLogManage.selectTotalRewardByUserId(loginUser.getId());
        BigDecimal todayReward = BigDecimal.ZERO;
        for (UserAmountLog item : todayAmountLogList) {
            if (item.getChangeType() == EAmtChangeType.HOR_REWARD.getCode()
                    || item.getChangeType() == EAmtChangeType.VER_REWARD.getCode()) {
                todayReward = todayReward.add(item.getChangeAmount());
            }
        }
        loginRsp.setTeamRewardOfToday(todayReward);
    }
}
