package com.tian.sakura.cdd.srv.service.mytask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineReq;
import com.tian.sakura.cdd.srv.web.mytask.dto.MyTaskLineReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTaskServiceTest {
	@Autowired
	private MyTaskService myTaskService;
	@Test
	public void list() {
		String userId="3dc4db16e90848e18bcb91553f35eff7";
		//关注中
		MyTaskLineReq req = new MyTaskLineReq();
		MyTaskLineReqBody body = new MyTaskLineReqBody();
		body.setUserId(userId);
		body.setPageNum(1);
		body.setPageSize(10);
		req.setBody(body);
		//Object obj1 = myTaskService.getMyFollow(req);
		//System.out.println("关注中我的任务，"+JSON.toJSON(obj1));
		//进行中我的任务
		Object obj2 = myTaskService.myTasking(req);
		System.out.println("进行中我的任务,"+JSON.toJSON(obj2));
		//进行中的战队任务
		//Object obj3 = myTaskService.teamTasking(req);
		//System.out.println("进行中的战队任务,"+JSON.toJSON(obj3));
		//进行中的队长推荐
		//Object obj4 = myTaskService.teamLeaderPrompting(req);
		//System.out.println("进行中的队长推荐,"+JSON.toJSON(obj4));
		//结算中我的任务
		//Object obj5 = myTaskService.mySettling(req);
		//System.out.println("结算中我的任务,"+JSON.toJSON(obj5));
		//Object obj6 = myTaskService.myTeamSettling(req);
		//System.out.println("结算中战队贡献,"+JSON.toJSON(obj6));

	}
}
