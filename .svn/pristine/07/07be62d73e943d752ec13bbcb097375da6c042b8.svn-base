package com.tian.sakura.cdd.order.acct;

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
public class UserAcctService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SUserManage userManage;

    public synchronized void changeUserAcct(UserAcctParameter acctParameter) {
        SUser dbUser = userManage.selectByPrimary(acctParameter.getUserId());

        SUser updUser = new SUser();
        updUser.setId(acctParameter.getUserId());
        boolean needUpdate = false;
        if (acctParameter.getChangeAmt() != null) {
            needUpdate = true;
            BigDecimal totalAmt = dbUser.getTotalAmount();
            if (totalAmt == null) {
                totalAmt = BigDecimal.ZERO;
            }
            totalAmt = totalAmt.add(acctParameter.getChangeAmt());
            logger.info("用户[{}]的现有金额[{}],变化金额[{}],变化后的金额[{}]",dbUser.getUserName(),
                    dbUser.getTotalAmount(), acctParameter.getChangeAmt(), totalAmt);
            updUser.setTotalAmount(totalAmt);
        }

        if (acctParameter.getChagngRewardBean() != null) {
            needUpdate = true;
            Integer rewardBean = dbUser.getRewardBean();
            if (rewardBean == null) {
                rewardBean = 0;
            }
            rewardBean = rewardBean + acctParameter.getChagngRewardBean();
            logger.info("用户[{}]的现有拆豆[{}],变化拆豆[{}],变化后的拆豆[{}]",dbUser.getUserName(),
                    dbUser.getRewardBean(), acctParameter.getChagngRewardBean(), rewardBean);

            updUser.setRewardBean(rewardBean);
        }

        if (acctParameter.getChangeLockAmt() != null) {
            needUpdate = true;
            BigDecimal lockAmt = dbUser.getLockAmount();
            if (lockAmt == null) {
                lockAmt = BigDecimal.ZERO;
            }
            lockAmt = lockAmt.add(acctParameter.getChangeLockAmt());
            logger.info("用户[{}]的现有冻结金额[{}],变化冻结金额[{}],变化后的冻结金额[{}]",dbUser.getUserName(),
                    dbUser.getLockAmount(), acctParameter.getChangeLockAmt(), lockAmt);

            updUser.setLockAmount(lockAmt);
        }
        if (needUpdate) {
            userManage.updateByPrimaryKeySelective(updUser);
        }



    }
}
