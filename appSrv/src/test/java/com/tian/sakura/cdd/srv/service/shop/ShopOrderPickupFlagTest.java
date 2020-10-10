package com.tian.sakura.cdd.srv.service.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.service.order.PrdOrderApiServcie;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ShopOrderPickupFlagTest {

	@Autowired
	private PrdOrderApiServcie prdOrderApiServcie;
	
	@Test
	public void pickupFlagTest() {
		SUser user = new SUser();
		user.setId("3dc4db16e90848e18bcb91553f35eff7");
		PrdOrderDetailQueryReq req = new PrdOrderDetailQueryReq();
		PrdOrderDetailQueryReqBody body = new PrdOrderDetailQueryReqBody();
		req.setBody(body);
		body.setId("05fdb8f4c637cfc689d355b6c54f5ad1");
		//prdOrderApiServcie.setPickupFlag(user, req.getBody());
	}
}
