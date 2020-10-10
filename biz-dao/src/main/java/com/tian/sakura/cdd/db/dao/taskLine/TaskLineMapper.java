package com.tian.sakura.cdd.db.dao.taskLine;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TaskLineMapper extends AbstractSingleMapper<TaskLine, String> {
	// 已满任务线个数
	public Integer getFulleTaskLineCntByProductId(String productId);

	List<TaskLine> getSettleTaskLineByProductId(@Param("productId") String productId,
					@Param("list") List<String> ids, @Param("taskLineCnt")int taskLineCnt);

	// 根据商品ID,查询当前未满任务线的份额数
	public Integer receivedTaskCntByProductId(String productId);

	// 根据任务ID,查询当前未满任务线的份额数
	public Integer receivedTaskCntByTaskId(String taskId);

	// 查询可以分派的任务线
	List<TaskLine> selectByProductIdForDispatch(Map<String, Object> params);

	// 根据状态查询
	public Integer getTaskLineByStatus(@Param("productId") String productId, @Param("lineStatus") Integer lineStatus,
			@Param("settleStatus") Integer settleStatus);

    void deleteByProductId(@Param("productId") String productId);

	Integer getLineNumberByPorduct(@Param("productId") String productId);

	int updateSettleStatusByOrderId(@Param("orderProductId")String orderDetailid, @Param("settleStatus")int settleStatus);
}