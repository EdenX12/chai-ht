package com.tian.sakura.cdd.srv.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.db.manage.task.TaskLineManage;

/**
 * 任务线相关
 * 已拆满的任务线个数
 * 当前未满任务线的份额数
 * 参与的拆家人数
 * @author liuhg
 *
 */
@Service
public class TaskLineService {
	@Autowired
	private TaskLineManage taskLineManage;
	
	//根据商品ID,查询已拆满的任务线个数
	public int getFullTaskLineCntByProductId(String productId) {
		return taskLineManage.getFulleTaskLineCntByProductId(productId);
	}
	
	//根据商品ID,查询当前未满任务线的份额数
	public int receivedTaskCntByProductId(String productId) {
		return taskLineManage.receivedTaskCntByProductId(productId);
	}
	//该商品参与的拆家人数
	public int userCount(String productId) {
		return taskLineManage.userCount(productId);
	}
	
	//根据任务ID,查询当前未满任务线的份额数
	public int receivedTaskCntByTaskId(String productId) {
		return taskLineManage.receivedTaskCntByTaskId(productId);
	}
	
}
