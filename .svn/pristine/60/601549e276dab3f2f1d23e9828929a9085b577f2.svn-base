package com.tian.sakura.cdd.db.dao.userBonusLog;

import java.math.BigDecimal;
import java.util.List;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.userBonusLog.UserBonusLog;
import org.apache.ibatis.annotations.Param;

/**
 *  冻结流水
 */
public interface UserBonusLogMapper extends AbstractSingleMapper<UserBonusLog,String>{
	//根据任务线id查询任务躺赢
	public BigDecimal selectBonusAmountByTaskLineId(String taskLineId);
	//根据任务线id查询组织躺赢
	public BigDecimal selectTeamBonusAmountByTaskLineId(String taskLineId);

	int batchInsert(List<UserBonusLog> userBonusLogs);
	//根据订单明细id查询所有的冻结记录
	List<UserBonusLog> selectByOrderDetailId(String orderDetailId);

	//躺赢人次
    public Integer selectCntByProductId(String productId);

    int updateStatusByDetailIdForSettle(@Param("orderDetailId") String detailId, @Param("logStatus")int status);

}