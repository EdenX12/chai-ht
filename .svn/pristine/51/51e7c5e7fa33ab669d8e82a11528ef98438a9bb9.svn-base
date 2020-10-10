package com.tian.sakura.cdd.db.manage.task;

import com.tian.sakura.cdd.common.dict.ESettleStatus;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.taskLine.TaskLineMapper;
import com.tian.sakura.cdd.db.dao.userTaskLine.UserTaskLineMapper;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务线相关
 * @author liuhg
 *
 */
@Service
@Transactional
public class TaskLineManage extends AbstractSingleManage<TaskLine ,String>{
	@Autowired
	private TaskLineMapper taskLineMapper;
	@Autowired
	private UserTaskLineMapper userTaskLineMapper;
	
	@Override
	protected AbstractSingleMapper<TaskLine, String> getSingleMapper() {
		return taskLineMapper;
	}

	// 查询待结算的任务线
	public List<TaskLine> getSettleTaskLineByProductId(String productId, List<String> ids, int size) {
		return taskLineMapper.getSettleTaskLineByProductId(productId, ids, size);
	}

	//已满任务线个数
	public Integer getFulleTaskLineCntByProductId(String productId) {

		Integer cnt =  taskLineMapper.getFulleTaskLineCntByProductId(productId);
		return cnt == null ? 0 : cnt;
	}
	//根据商品ID,查询当前未满任务线的份额数
	public int receivedTaskCntByProductId(String productId) {
		Integer cnt =  taskLineMapper.receivedTaskCntByProductId(productId);
		return cnt == null ? 0 : cnt;
	}
	//该商品参与的拆家人数
	public Integer userCount(String productId) {
		Integer cnt = userTaskLineMapper.userCount(productId);
		return cnt == null ? 0 : cnt;
	}

	public 	List<TaskLine> selectByProductIdForDispatch(String productId, Integer lineOrder, int taskCnt) {
		Map<String,Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("lineOrder", lineOrder);
		params.put("taskCnt", taskCnt);
		return taskLineMapper.selectByProductIdForDispatch(params);
	}

	//根据任务ID,查询当前未满任务线的份额数
	public int receivedTaskCntByTaskId(String taskId) {
		Integer cnt =  taskLineMapper.receivedTaskCntByTaskId(taskId);
		return cnt == null ? 0 : cnt;
	}

	//返回用户正在进行中的任务线 
	public List<UserTaskLine> getTaskLine(String userId,String productId){
		return userTaskLineMapper.getTaskLine(userId,productId);
	};
	//根据task_line_id查询 
	public  List<UserTaskLine> qryByTaskLineId(String taskLineId){
		return userTaskLineMapper.qryByTaskLineId(taskLineId);
	};



	//下单，支付前，锁定任务
	public void lockTaskByIds(List<String> ids) {
		userTaskLineMapper.lockTaskByIds(ids);

	}
	//支付成功后，解锁任务数 锁定任务-1， 接收任务+1
	public void unLockTaskByIds(List<String> ids) {
		userTaskLineMapper.unLockTaskByIds(ids);
	}
	//未支付的任务，释放锁定任务 -1
	public void cancelLockTaskByIds(List<String> ids) {
		userTaskLineMapper.cancelLockTaskByIds(ids);
	}
	
	//根据状态查询
	public Integer getTaskLineByStatus(String productId,Integer lineStatus,Integer settleStatus) {
		return taskLineMapper.getTaskLineByStatus(productId, lineStatus, settleStatus);
	}

    public void deleteByProductId(String id) {
		taskLineMapper.deleteByProductId(id);
    }

	public Integer getLineNumberByPorduct(String id) {
		return taskLineMapper.getLineNumberByPorduct(id);
	}

	public int updateSettleStatusByOrderId(String orderDetailid, ESettleStatus settleStatus) {
		return taskLineMapper.updateSettleStatusByOrderId(orderDetailid, settleStatus.getCode());
	}
}
