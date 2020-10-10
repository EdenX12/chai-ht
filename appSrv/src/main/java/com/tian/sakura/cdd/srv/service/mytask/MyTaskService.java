package com.tian.sakura.cdd.srv.service.mytask;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 我的任务篮
 * @author liuhg
 *
 */
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.task.MyTask;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.mytask.MyTaskManage;
import com.tian.sakura.cdd.db.manage.mytask.vo.MyTaskQueryVo;
import com.tian.sakura.cdd.db.manage.mytask.vo.UserRelationVo;
import com.tian.sakura.cdd.db.manage.product.ProductReviewStatManage;
import com.tian.sakura.cdd.db.manage.product.vo.MyTaskProductVo;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.srv.service.product.PrdCommissionCalculateResult;
import com.tian.sakura.cdd.srv.service.product.ProductCommissionService;
import com.tian.sakura.cdd.srv.service.product.param.DefaultPrdCommissionParameter;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineReq;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineReqBody;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.MyTaskRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.MyTeamTaskRspBody;


@Service
public class MyTaskService {
	@Autowired
	private MyTaskManage myTaskManage;
	@Autowired
	private ProductCommissionService productCommissionService;
	@Autowired
	private TaskLineManage taskLineManage;
	@Autowired
	private ProductReviewStatManage productReviewStatManage;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	

	private ProductRspBody doBuildProductRspBody(Product product) {
		ProductRspBody productRspBody = new ProductRspBody();
		BeanUtils.copyProperties(product, productRspBody);
		DefaultPrdCommissionParameter commissionParameter = new DefaultPrdCommissionParameter(product.getId(),
				product.getTaskNumber(), product.getTotalReward());
		PrdCommissionCalculateResult calculateResult = productCommissionService
				.getProductCommission(commissionParameter);
		BeanUtils.copyProperties(calculateResult, productRspBody);
		// 我的任务篮，当前未满任务线上任务数是根据任务id获取的
		productRspBody.setReceivedTask(calculateResult.getCurrentReceivedTask());

		return productRspBody;
	}

	// 获取关注中的商品
	public PageInfo getMyFollow(MyTaskLineReq req) {
		MyTaskQueryVo queryVo = MyTaskQueryVo.builder().userId(req.getBody().getUserId()).build();
		queryVo.setPageNum(req.getBody().getPageNum());
		queryVo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());
		List<Product> products = myTaskManage.getFollow(queryVo);
		PageInfo<Product> pageInfo = PageInfo.of(products);
		PageInfo<ProductRspBody> pageInfo2 = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, pageInfo2);

		List<ProductRspBody> l = new ArrayList<ProductRspBody>();
		for (Product product : products) {
			ProductRspBody productRspBody = doBuildProductRspBody(product);
			l.add(productRspBody);
		}
		pageInfo2.setList(l);
		return pageInfo2;
	}

	// 进行中我的任务
	public PageInfo myTasking(MyTaskLineReq req) {
		return makeUserProductTask(req);
	}

	// 进行中的战队任务
	public PageInfo teamTasking(MyTaskLineReq req) {
		MyTaskQueryVo queryVo = MyTaskQueryVo.builder().userId(req.getBody().getUserId()).build();
		queryVo.setPageNum(req.getBody().getPageNum());
		queryVo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());

		// 查询用户的直接一级下级
		List<UserRelationVo> users = myTaskManage.getOneLevel(queryVo);
		// 根据userId查询商品
		List<MyTeamTaskRspBody> l = new ArrayList<MyTeamTaskRspBody>();
		for (UserRelationVo user : users) {
			List<HashMap> tasks = myTaskManage.teamTasking(user.getUserId());
			for (HashMap map : tasks) {
				String productId = (String) map.get("productId");// 商品id
				Long taskLineingTotal = (Long) map.get("taskLineTotal");// 该商品进行中的任务线个数
				// 查询用户该商品的总任务线数量
				int totalTaskLine = myTaskManage.totalTaskLineByUserIdPrd(user.getUserId(), productId);
				// 查询该商品最新一条任务线
				Product product = myTaskManage.myLatestTaskLine(productId, user.getUserId());
				if(product == null) {
					continue;
				}
				ProductRspBody productRspBody = doBuildProductRspBody(product);

				MyTeamTaskRspBody team = new MyTeamTaskRspBody();

				BeanUtils.copyProperties(productRspBody, team);

				team.setUserTotalTaskLine(totalTaskLine);// 总的任务线个数
				team.setUserTotalTaskingLine(taskLineingTotal.intValue());// 进行中的任务线个数
				team.setUserId(user.getUserId());// 用户id
				team.setNickName(user.getNickName());
				team.setUserName(user.getUserName());

				l.add(team);

			}
		}

		PageInfo<UserRelationVo> pageInfo = PageInfo.of(users);
		PageInfo<MyTeamTaskRspBody> pageInfo2 = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, pageInfo2);
		pageInfo2.setList(l);

		return pageInfo2;
	}

	// 进行中的队长推荐
	public PageInfo teamLeaderPrompting(MyTaskLineReq req) {
		// 查询用户的直接上级
		UserRelation userRelation = myTaskManage.getUpLevel(req.getBody().getUserId());
		if(userRelation != null && userRelation.getParentId() != null) {
			req.getBody().setUserId(userRelation.getParentId());
			return makeUserProductTask(req);
		}else {
			PageInfo<MyTaskRspBody> pageInfo = new PageInfo<>();
			return pageInfo;
		}
		
	}

	private PageInfo makeUserProductTask(MyTaskLineReq req) {

		MyTaskQueryVo queryVo = MyTaskQueryVo.builder().userId(req.getBody().getUserId()).build();
		queryVo.setPageNum(req.getBody().getPageNum());
		queryVo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());

		List<MyTaskRspBody> l = new ArrayList<>();
		// 进行中的任务线，按商品
		List<HashMap> productTaskLines = myTaskManage.myTasking(queryVo);
		for (HashMap map : productTaskLines) {
			// 商品id
			String productId = (String) map.get("productId");
			// 该商品进行中的任务线个数
			Long taskLineingTotal = (Long) map.get("taskLineTotal");
			
			logger.info("--userid"+req.getBody().getUserId());
			logger.info("--productId"+productId);
			
			// 查询用户该商品的总任务线数量
			int totalTaskLine = myTaskManage.totalTaskLineByUserIdPrd(req.getBody().getUserId(), productId);
			// 查询该商品最新一条任务线(进行中)
			Product product = myTaskManage.myLatestTaskLine(productId, req.getBody().getUserId());
			if(product == null) {
				continue;
			}
			ProductRspBody productRspBody = doBuildProductRspBody(product);
			MyTaskRspBody myTaskRspBody = new MyTaskRspBody();
			BeanUtils.copyProperties(productRspBody, myTaskRspBody);
			// 总的任务线个数
			myTaskRspBody.setUserTotalTaskLine(totalTaskLine);
			// 进行中的任务线个数
			myTaskRspBody.setUserTotalTaskingLine(taskLineingTotal.intValue());
			// 用户id
			myTaskRspBody.setUserId(req.getBody().getUserId());
			l.add(myTaskRspBody);
		}
		PageInfo<HashMap> pageInfo = PageInfo.of(productTaskLines);
		PageInfo<MyTaskRspBody> pageInfo2 = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, pageInfo2);
		pageInfo2.setList(l);
		return pageInfo2;
	}

	// 返回用户正在进行中的任务线
	public List<MyTaskLineRspBody> qryTaskLine(String userId, String productId) {
		List<UserTaskLine> userTaskLines = taskLineManage.getTaskLine(userId, productId);
		List<MyTaskLineRspBody> l = new ArrayList<MyTaskLineRspBody>();
		for (UserTaskLine userTaskLine : userTaskLines) {
			List<UserTaskLine> taskLines = taskLineManage.qryByTaskLineId(userTaskLine.getTaskLineId());
			MyTaskLineRspBody body = new MyTaskLineRspBody();
			body.setTaskLineId(userTaskLine.getTaskLineId());
			body.setTotalPeople(taskLines.size());
			List<HashMap> mapList = new ArrayList<HashMap>();
			int i = 0;
			for (UserTaskLine t : taskLines) {
				if (t.getUserId().contentEquals(userId)) {
					HashMap m = new HashMap();
					m.put("taskId", t.getId());
					m.put("position", i);
					mapList.add(m);
				}
				i++;
			}
			body.setMyPosition(mapList);
			l.add(body);
		}
		return l;
	}

	// 结算中我的任务
	public PageInfo mySettling(MyTaskLineReq req) {
		MyTaskQueryVo queryVo = MyTaskQueryVo.builder().userId(req.getBody().getUserId()).build();
		queryVo.setPageNum(req.getBody().getPageNum());
		queryVo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());

		List<MyTaskRspBody> l = new ArrayList<MyTaskRspBody>();
		// 结算中的任务线，按商品
		List<HashMap> productTasks = myTaskManage.mySettleTask(queryVo);
		for (HashMap map : productTasks) {
			// 商品id
			String productId = (String) map.get("productId");
			// 该商品结算中的任务线个数
			Long taskLineingTotal = (Long) map.get("taskLineTotal");
			// 查询用户该商品的总任务线数量
			int totalTaskLine = myTaskManage.totalTaskLineByUserIdPrd(req.getBody().getUserId(), productId);
			// 查询该商品最新一条任务线(结算中)
			MyTaskProductVo product = myTaskManage.myLatestSettleTaskLine(productId, req.getBody().getUserId());
			// 结算中的躺赢金
			String taskLineId = product.getTaskLineId();
			BigDecimal bonusAmount = myTaskManage.getBonusAmount(taskLineId);
			// 任务线已拆满
			int totalNumber = taskLineManage.getFulleTaskLineCntByProductId(productId);
			// 当前任务线人数
			int receivedNumber = taskLineManage.receivedTaskCntByProductId(productId);
			// 参与拆家人数
			int userCnt = taskLineManage.userCount(productId);
			// 关注人数
			int totalFocus = productReviewStatManage.totalFocus(productId);
			ProductRspBody prdRspBody = new ProductRspBody();
			BeanUtils.copyProperties(product, prdRspBody);
			prdRspBody.setEveryReward(bonusAmount);// 任务躺赢
			prdRspBody.setTotalNumber(totalNumber);// 已拆满任务线数量
			prdRspBody.setReceivedTask(receivedNumber);// 当前任务线人数
			prdRspBody.setUserCount(userCnt);// 参与拆家人数
			prdRspBody.setTotalFocus(totalFocus);// 关注人数
			MyTaskRspBody myTaskRspBody = new MyTaskRspBody();
			BeanUtils.copyProperties(prdRspBody, myTaskRspBody);
			// 总的任务线个数
			myTaskRspBody.setUserTotalTaskLine(totalTaskLine);
			// 进行中的任务线个数
			myTaskRspBody.setUserTotalTaskingLine(taskLineingTotal.intValue());
			// 用户id
			myTaskRspBody.setUserId(req.getBody().getUserId());
			l.add(myTaskRspBody);
		}
		PageInfo<HashMap> pageInfo = PageInfo.of(productTasks);
		PageInfo<MyTaskRspBody> pageInfo2 = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, pageInfo2);
		pageInfo2.setList(l);
		return pageInfo2;
	}

	// 结算中战队贡献
	public PageInfo myTeamSettling(MyTaskLineReq req) {
		MyTaskQueryVo queryVo = MyTaskQueryVo.builder().userId(req.getBody().getUserId()).build();
		queryVo.setPageNum(req.getBody().getPageNum());
		queryVo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());
		// 查询用户的直接一级下级
		List<UserRelationVo> users = myTaskManage.getOneLevel(queryVo);
		// 根据userId查询商品
		List<MyTeamTaskRspBody> l = new ArrayList<MyTeamTaskRspBody>();
		for (UserRelationVo user : users) {
			// 结算中的任务线，按商品
			List<HashMap> productTasks = myTaskManage.mySettleTaskByUserId(user.getUserId());
			for (HashMap map : productTasks) {
				String productId = (String) map.get("productId");// 商品id
				Long taskLineingTotal = (Long) map.get("taskLineTotal");// 该商品结算中的任务线个数
				// 查询用户该商品的总任务线数量
				int totalTaskLine = myTaskManage.totalTaskLineByUserIdPrd(user.getUserId(), productId);
				// 查询该商品最新一条任务线(结算中)
				MyTaskProductVo product = myTaskManage.myLatestSettleTaskLine(productId, user.getUserId());
				// 结算中的躺赢金
				String taskLineId = product.getTaskLineId();
				BigDecimal bonusAmount = myTaskManage.getBonusAmount(taskLineId);
				// 结算中组织躺赢奖
				BigDecimal teamBonusAmount = myTaskManage.getTeamBonusAmount(taskLineId);

				// 任务线已拆满
				int totalNumber = taskLineManage.getFulleTaskLineCntByProductId(productId);
				// 当前任务线人数
				int receivedNumber = taskLineManage.receivedTaskCntByProductId(productId);
				// 参与拆家人数
				int userCnt = taskLineManage.userCount(productId);
				// 关注人数
				int totalFocus = productReviewStatManage.totalFocus(productId);

				ProductRspBody prdRspBody = new ProductRspBody();
				BeanUtils.copyProperties(product, prdRspBody);
				prdRspBody.setEveryReward(bonusAmount);// 任务躺赢
				prdRspBody.setTotalNumber(totalNumber);// 已拆满任务线数量
				prdRspBody.setReceivedTask(receivedNumber);// 当前任务线人数
				prdRspBody.setUserCount(userCnt);// 参与拆家人数
				prdRspBody.setTotalFocus(totalFocus);// 关注人数

				MyTeamTaskRspBody team = new MyTeamTaskRspBody();
				BeanUtils.copyProperties(prdRspBody, team);

				team.setUserTotalTaskLine(totalTaskLine);// 总的任务线个数
				team.setUserTotalTaskingLine(taskLineingTotal.intValue());// 进行中的任务线个数
				team.setUserId(req.getBody().getUserId());// 用户id
				team.setNickName(user.getNickName());
				team.setUserName(user.getUserName());

				l.add(team);

			}

		}
		PageInfo<UserRelationVo> pageInfo = PageInfo.of(users);
		PageInfo<MyTeamTaskRspBody> pageInfo2 = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, pageInfo2);
		pageInfo2.setList(l);
		return pageInfo2;
	}

}
