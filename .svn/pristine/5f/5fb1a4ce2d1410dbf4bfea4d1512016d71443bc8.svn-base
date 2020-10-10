package com.tian.sakura.cdd.srv.service.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopDataReq;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopDataReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopTodayDataReq;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopTodayDataReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopDataServiceTest {
	@Autowired
	private ShopDataService shopDataService;
//	@Test
//	public void todayTest() {
//		SUser user = new SUser(); 
//		ShopTodayDataReq req = new ShopTodayDataReq();
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		ShopTodayDataReqBody body = new ShopTodayDataReqBody();
//		req.setBody(body);
//		body.setQryDurationType(3);
//		body.setStartDate(null);
//		body.setEndDate(null);
//		
//		Object obj = shopDataService.getShopTodayData(user, req);
//		System.out.println(JSON.toJSONString(obj));
//	}
	
	@Test
	public void shopData() {
		SUser user = new SUser();
		user.setId("3dc4db16e90848e18bcb91553f35eff7");
		ShopDataReq req = new ShopDataReq();
		ShopDataReqBody body = new ShopDataReqBody();
		req.setBody(body);
		Object obj = shopDataService.getShopData(user, req);
		System.out.println(JSON.toJSONString(obj));
	}
}
