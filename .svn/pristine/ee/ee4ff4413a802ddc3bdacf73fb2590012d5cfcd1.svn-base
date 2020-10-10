package com.tian.sakura.cdd.srv.service.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopHeaderServiceTest {

	@Autowired
	private ShopHeaderService service;
	
//	@Test
//	public void topTest() {
//		String userId = "999";
//		ShopHeaderRspBody rsp = service.getShopHeaderTop(userId);
//		System.out.println(rsp.toString());
//	}

//	@Test
//	public void shopHeader() {
//		ShopReqBody reqBody = new ShopReqBody();
//		reqBody.setShopId(20);
//		String userId = null;
//		//PageInfo pg =service.getShopHeaderNewProduct(userId,reqBody);
//		PageInfo pg =service.getShopHeader(userId,reqBody);
//		System.out.println(pg);
//	}

//	@Test
//	public void shopHeaderGroupPrd() {
//		String userId = null;
//		ShopReqBody body = new ShopReqBody();
//		body.setShopId(1);
//		body.setGroupId(16);
//		Object obj =service.getGroupPrd(userId,body);
//		System.out.println(obj.toString());
//	}

	@Test
	public void searchPrd() {
		String userId = null;
		ShopReqBody body = new ShopReqBody();
		body.setShopId(1);
		body.setProductName("麻辣");
		Object obj =service.productSearch(userId,body);
		System.out.println(obj.toString());
	}

}
