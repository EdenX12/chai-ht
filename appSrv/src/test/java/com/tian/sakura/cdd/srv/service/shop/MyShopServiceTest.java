package com.tian.sakura.cdd.srv.service.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.srv.web.shop.dto.MyShopReq;
import com.tian.sakura.cdd.srv.web.shop.dto.MyShopRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderRspBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class MyShopServiceTest {
	@Autowired
	private MyShopService myShopService;
	@Test
	public void test() {
		String userId = "3dc4db16e90848e18bcb91553f35eff7";
		ShopHeaderRspBody rsp = myShopService.getQrCode(userId);
		System.out.println(rsp);
		
	}
//	@Test
//	public void accountTest() {
//		String userId = "3dc4db16e90848e18bcb91553f35eff7";
//		MyShopReq req = new MyShopReq();
//		MyShopRspBody rsp = myShopService.getShopAccountOrder(userId, req);
//		System.out.println(rsp);
//		
//	}

}
