package com.tian.sakura.cdd.srv.service.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.service.order.PrdOrderQueryService;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReqBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderSendReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderSendReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderReq;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ShopOrderServiceTest {
	@Autowired
	private PrdOrderQueryService service;
	@Autowired
	private ShopOrderService shopOrderService;
	//商铺订单查询
	@Test
	public void orderTest() {
		Integer qryDurationType = null;
		//0; //待付款
		//1 已付款，待发货
		//2 已发货
		String orderStatus = null;
		String userId = "3dc4db16e90848e18bcb91553f35eff7";
		SUser user = new SUser();
		user.setId(userId);
		ShopOrderReqBody reqBody = new ShopOrderReqBody();
		reqBody.setOrderStatus(orderStatus);
		reqBody.setQryDurationType(qryDurationType);
		//reqBody.setOrderSn("ddsds");
		//reqBody.setAddressName("dsds");
		reqBody.setAddressPhone("dsds");
		Object obj = service.selectShopOrderByPage(user, reqBody);
		System.out.println(JSON.toJSONString(obj));
				
	}
//	//置已取货状态
//	@Test
//	public void pickupFlag() {
//		SUser user = new SUser();
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		PrdOrderDetailQueryReq req = new PrdOrderDetailQueryReq();
//		PrdOrderDetailQueryReqBody body = new PrdOrderDetailQueryReqBody();
//		body.setId("05fdb8f4c637cfc689d355b6c54f5ad1");
//		req.setBody(body);
//		shopOrderService.setPickupFlag(user, req.getBody());
//	}
//	
//	//商家发货
//	@Test
//	public void delivery() {
//		SUser user = new SUser();
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		PrdOrderSendReq req = new PrdOrderSendReq();
//		PrdOrderSendReqBody body = new PrdOrderSendReqBody();
//		req.setBody(body);
//		body.setOrderDetailId("05fdb8f4c637cfc689d355b6c54f5ad1");
//		body.setShippingCode("test");
//		body.setShippingExpressId(1);
//		
//		shopOrderService.sendPrdOrder(user, req);
//	}
}
