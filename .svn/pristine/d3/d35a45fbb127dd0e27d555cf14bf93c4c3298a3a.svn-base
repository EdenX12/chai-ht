package com.tian.sakura.cdd.srv.service.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.srv.web.shop.dto.CorpRspBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class QryInfoServiceTest {

	@Autowired
	private CorpService corpService;

	@Test
	public void qryShop() {
		
		CorpRspBody body = corpService.qryShopInfo("3dc4db16e90848e18bcb91553f35eff7");
		System.out.println(body.toString());
	}

}
