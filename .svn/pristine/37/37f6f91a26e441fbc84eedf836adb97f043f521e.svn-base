package com.tian.sakura.cdd.db.manage.log;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.log.UserAmountLogMapper;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserAmountLogManage extends AbstractSingleManage<UserAmountLog, String> {

    @Autowired
    private UserAmountLogMapper userAmountLogMapper;

    @Override
    protected AbstractSingleMapper<UserAmountLog, String> getSingleMapper() {
        return userAmountLogMapper;
    }

    public List<UserAmountLog> getUserAmountLogByNum(Integer qryNumber) {
        return userAmountLogMapper.getUserAmountLogByNum(qryNumber);
    }

    public List<UserAmountLog> selectByPage(AmountLogQueryVo queryVo) {
        return userAmountLogMapper.selectByPage(queryVo);
    }

    public List<UserAmountLog> selectTotalRewardByUserId(String userId) {
        AmountLogQueryVo queryVo = new AmountLogQueryVo();
        queryVo.setUserId(userId);

        return userAmountLogMapper.selectTotalReward(queryVo);
    }

    public List<UserAmountLog> selectTodayRewardByUserId(String userId) {
        AmountLogQueryVo queryVo = new AmountLogQueryVo();
        queryVo.setUserId(userId);
        //格式化日期
        queryVo.setBeginDate(DateTime.now().minusDays(-1).toDate());
        queryVo.setEndDate(DateTime.now().minusDays(1).toDate());

        return userAmountLogMapper.selectTotalReward(queryVo);
    }
}
