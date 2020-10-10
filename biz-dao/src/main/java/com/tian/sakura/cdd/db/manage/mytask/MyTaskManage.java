package com.tian.sakura.cdd.db.manage.mytask;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.product.ProductMapper;
import com.tian.sakura.cdd.db.dao.user.UserRelationMapper;
import com.tian.sakura.cdd.db.dao.userBonusLog.UserBonusLogMapper;
import com.tian.sakura.cdd.db.dao.userTaskLine.UserTaskLineMapper;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.task.MyTask;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.mytask.vo.MyTaskQueryVo;
import com.tian.sakura.cdd.db.manage.mytask.vo.UserRelationVo;
import com.tian.sakura.cdd.db.manage.product.vo.MyTaskProductVo;

@Service
public class MyTaskManage extends AbstractSingleManage<Product,String> {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private UserTaskLineMapper userTaskLineMapper;
    @Autowired
    private UserRelationMapper userRelationMapper;
    @Autowired
    private UserBonusLogMapper userBonusLogMapper;

	
	@Override
	protected AbstractSingleMapper<Product, String> getSingleMapper() {
		
		return productMapper;
	}
	public List<Product> getUserProducts(MyTaskQueryVo queryVo){
		return productMapper.getUserProducts(queryVo);
	}
	//获取关注中的商品
	public List<Product> getFollow(MyTaskQueryVo queryVo){
		return productMapper.getUserFollow(queryVo);
	}
	//进行中的任务线个数 分页
	public  List<HashMap> myTasking(MyTaskQueryVo queryVo){
		
		return userTaskLineMapper.qryTaskLineByUserId(queryVo);
	}
	//进行中的最新一条任务线
	public  Product myLatestTaskLine(String productId,String userId){
		return productMapper.myTasking(productId, userId);
	}

	//结算中的最新一条任务线
	public  MyTaskProductVo myLatestSettleTaskLine(String productId,String userId){
		return productMapper.myLatestSettleTaskLine(productId, userId);
	}

	//进行中的战队任务线个数 不分页
	public  List<HashMap> teamTasking(String userId){
		return userTaskLineMapper.qryTaskLine(userId);
	}

	//进行中的队长推荐
	public  List<MyTask> teamLeaderPrompting(MyTaskQueryVo queryVo){
		return productMapper.teamLeaderPrompting(queryVo);
	}

	//用户某个商品的任务线总数
	public int  totalTaskLineByUserIdPrd(String userId,String productId) {
		Integer cnt = userTaskLineMapper.totalTaskLineByUserIdPrd(userId,productId);
		return cnt == null ? 0 : cnt;
	}
	//用户任务线总数
	public int  totalTaskLineByUserId(String userId) {
		Integer cnt = userTaskLineMapper.totalTaskLineByUserId(userId);
		return cnt == null ? 0 : cnt;
	}
	
	
	//用户进行中的任务数
	public int  totalTaskLineingByUserId(String userId) {
		Integer cnt = userTaskLineMapper.totalTaskLineingByUserId(userId);
		return cnt == null ? 0 : cnt;
	}
	
	//查询用户的一级下级
	public List<UserRelationVo> getOneLevel(MyTaskQueryVo queryVo){
		return userRelationMapper.getOneLevel(queryVo);
	}
	//查询用户的上级
	public UserRelation  getUpLevel(String userId){
		return userRelationMapper.getUpLevel(userId);
	}
	//用户结算中的任务线
	public int  totalTaskMySettling(String userId) {
		Integer cnt = userTaskLineMapper.totalTaskSettleByUserId(userId);
		return cnt == null ? 0 : cnt;
	}
	//结算中的任务 分页
	public  List<HashMap> mySettleTask(MyTaskQueryVo queryVo){
		return userTaskLineMapper.qrySettleTaskByPage(queryVo);
	}

	//结算中的任务 不分页
	public  List<HashMap> mySettleTaskByUserId(String userId){
		return userTaskLineMapper.qrySettleTaskByUserId(userId);
	}

	//结算中的任务躺赢金
	public BigDecimal getBonusAmount(String taskLineId) {
		BigDecimal bonusAmount = userBonusLogMapper.selectBonusAmountByTaskLineId(taskLineId);
		return bonusAmount == null ? BigDecimal.ZERO:bonusAmount;
	}
	//结算中的组织躺赢金
	public BigDecimal getTeamBonusAmount(String taskLineId) {
		BigDecimal bonusAmount = userBonusLogMapper.selectTeamBonusAmountByTaskLineId(taskLineId);
		return bonusAmount == null ? BigDecimal.ZERO:bonusAmount;
	}

}
