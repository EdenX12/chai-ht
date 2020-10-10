package com.tian.sakura.cdd.srv.web.mytask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.mytask.MyTaskService;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineReq;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineReqBody;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.MyTaskRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.MyTeamTaskRspBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("myTasks")
@Api("任务篮")

public class MyTaskController {
	@Autowired
	private MyTaskService myTaskService;
	///////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-关注中-任务关注")
    @PostMapping("/taskFocus")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo getMyFollow(@RequestBody MyTaskLineReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		req.getBody().setUserId(user.getId());
		return myTaskService.getMyFollow(req);
	}
	//////////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-关注中-转移关注")
    @PostMapping("/transferTaskFollow")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public List<ProductRspBody> getTransfer() {
		SUser user = LoginUserThreadLocal.getLoginUser();
		return null;
	}
	/////////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-进行中-我的任务")
    @PostMapping("/myTasking")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo myTasking(@RequestBody MyTaskLineReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		req.getBody().setUserId(user.getId());
		return myTaskService.myTasking(req);
	}
	////////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-进行中-战队任务")
    @PostMapping("/teamTasking")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo teamTasking(@RequestBody MyTaskLineReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		req.getBody().setUserId(user.getId());
		return myTaskService.teamTasking(req);
	}
	//////////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-进行中-队长推荐")
    @PostMapping("/teamLeaderPrompting")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo teamLeaderPrompting(@RequestBody MyTaskLineReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		req.getBody().setUserId(user.getId());
		return myTaskService.teamLeaderPrompting(req);
	}
	////////////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-进行中的任务线")
    @PostMapping("/taskLineing")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public List<MyTaskLineRspBody>  qryTaskLineByUserId(@RequestBody MyTaskLineReq myTaskLineReq){
		SUser user = LoginUserThreadLocal.getLoginUser();
		myTaskLineReq.getBody().setUserId(user.getId());
		return myTaskService.qryTaskLine(myTaskLineReq.getBody().getUserId(),myTaskLineReq.getBody().getProductId());
	}
	//////////////////////////////////////////////////////////////////////////////
	
	@ApiOperation("任务篮-结算中-我的结算")
    @PostMapping("/mySettling")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo mySettling(@RequestBody MyTaskLineReq myTaskLineReq) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		myTaskLineReq.getBody().setUserId(user.getId());
		return myTaskService.mySettling(myTaskLineReq);
	}
	////////////////////////////////////////////////////////////////////////////////
	@ApiOperation("任务篮-结算中-战队贡献")
    @PostMapping("/myTeamSettling")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo myTeamSettling(@RequestBody MyTaskLineReq myTaskLineReq) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		myTaskLineReq.getBody().setUserId(user.getId());
		return myTaskService.myTeamSettling(myTaskLineReq);
	}
	
}
