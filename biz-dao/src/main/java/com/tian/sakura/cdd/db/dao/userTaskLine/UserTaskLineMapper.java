package com.tian.sakura.cdd.db.dao.userTaskLine;

import java.util.HashMap;
import java.util.List;

import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import org.apache.ibatis.annotations.Param;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;

import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.mytask.vo.MyTaskQueryVo;

public interface UserTaskLineMapper extends AbstractSingleMapper<UserTaskLine,String> {
	//该商品参与的拆家人数
	public Integer userCount(String productId);

	//根据用户id查询任务线数量
	public Integer totalTaskLineByUserIdPrd(@Param("userId")String userId,@Param("productId")String productId);

	//根据用户id查询任务线数量
	public Integer totalTaskLineByUserId(@Param("userId")String userId);

	//根据用户id查询正在进行中的任务线数量
	public Integer totalTaskLineingByUserId(String userId);
	
	//返回用户正在进行中的任务线  分页
	public List<HashMap> qryTaskLineByUserId(MyTaskQueryVo queryVo);
	
	//返回用户正在进行中的任务线  不分页
	public List<HashMap> qryTaskLine(String userId);

	//返回用户结算中的任务线  分页
	public List<HashMap> qrySettleTaskByPage(MyTaskQueryVo queryVo);

	//返回用户结算中的任务线  不分页
	public List<HashMap> qrySettleTaskByUserId(String userId);
	
	//返回用户正在进行中的任务线 
	public List<UserTaskLine> getTaskLine(@Param("userId")String userId,@Param("productId")String productId);

	//根据task_line_id查询 
	public  List<UserTaskLine> qryByTaskLineId(String taskLineId);
	//根据用户id查询结算中的任务线数量
	public Integer totalTaskSettleByUserId(String userId);

	//查询改用户可分配的任务线， 未满的 进行中的
	List<UserTaskLine>  selectForDispatch(String userId, String productId);
	//查询taskId下的任务线
	List<UserTaskLine> selectByTaskId(String taskId);
	// 分组prductId和status查询用户的任务线数量
	List<UserTaskLine> selectCntByUserIdGroupByPrdId(String userId);

	//锁定任务+1
	void lockTaskByIds(List<String> ids);
	//支付成功后，解锁任务数 锁定任务-1， 接收任务+1
	void unLockTaskByIds(List<String> ids);
    //未支付的任务，释放锁定任务 -1
	void cancelLockTaskByIds(List<String> ids);

	int batchUpdateByTaskId(UserTaskLine userTaskLine);


}