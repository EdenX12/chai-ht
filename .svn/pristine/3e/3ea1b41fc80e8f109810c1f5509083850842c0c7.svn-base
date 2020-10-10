package com.tian.sakura.cdd.db.dao.log;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.manage.log.AmountLogQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAmountLogMapper extends AbstractSingleMapper<UserAmountLog, String> {

    List<UserAmountLog> getUserAmountLogByNum(@Param("qryNumber") Integer qryNumber);

    /**
     * 查询用户的所有类型的收益汇总收益
     *
     * @param queryVo
     * @return
     */
    List<UserAmountLog> selectTotalReward(AmountLogQueryVo queryVo);

    /**
     * 查询收益明细
     *
     * @param queryVo
     * @return
     */
    List<UserAmountLog> selectByPage(AmountLogQueryVo queryVo);
}