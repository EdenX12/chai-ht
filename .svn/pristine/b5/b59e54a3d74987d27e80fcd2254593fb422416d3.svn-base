package com.tian.sakura.cdd.srv.service.shop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopDeleteGroupProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopDeleteGroupProductReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopMoveGroupProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopMoveGroupProductReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ShopGroupServiceTest {
	@Autowired
	private ShopGroupService shopGroupService;
	
//	@Test
//	public void createShopGroupTest() {
//		ShopGroupReq req = new ShopGroupReq();
//		ShopGroupReqBody body =  new ShopGroupReqBody();
//		String groupName ="渔具";
//		String userId ="555";
//		body.setGroupName(groupName);
//		shopGroupService.createShopGroup(userId, body);
//		
//	}
	
//	@Test
//	public void deletePrdInGroup() {
//		String userId = "a9e6b574f88644bc96eb1bd3064430ef";
//		ShopDeleteGroupProductReq req = new ShopDeleteGroupProductReq();
//		ShopDeleteGroupProductReqBody body = new ShopDeleteGroupProductReqBody();
//		Integer groupId = 1;
//		body.setGroupId(groupId);
//		List<String> l = new ArrayList<>();
//		l.add("b264a3be3ac640b7afc8448dbc172726");
//		body.setProductIds(l);
//		req.setBody(body);
//		shopGroupService.deletePrdInGroup(userId, req);
//	}
	
	@Test
	public void movePrdInGroup() {
		List<String> l = new ArrayList<>();
		l.add("13");
		String userId = "a9e6b574f88644bc96eb1bd3064430ef";
		ShopMoveGroupProductReq req =new ShopMoveGroupProductReq();
		ShopMoveGroupProductReqBody body = new ShopMoveGroupProductReqBody();
		req.setBody(body);
		body.setSourceGroupId(2);
		body.setTargetGroupId(1);
		body.setProductIds(l);
		shopGroupService.movePrdInGroup(userId, req);
	}
}
