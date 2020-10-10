package com.tian.sakura.cdd.srv.service.activityproduct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.srv.service.activityProduct.ActivityProductService;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityReq;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityProductServiceTest {
	@Autowired
	private ActivityProductService activityProductService;
	@Test
	public void list() {
		ActivityReq activityReq=new ActivityReq();
		ActivityReqBody body = new ActivityReqBody();
		activityReq.setBody(body);
		activityReq.getBody().setId("1");//活动id
		activityReq.getBody().setType(1);//类型 1-活动
		Object obj =activityProductService.getProductListByActityId(activityReq);
		System.out.println(JSON.toJSONString(obj));
	}
}
