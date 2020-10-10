package com.tian.sakura.cdd.srv.service.shop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.srv.web.shop.dto.CatalogRspBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CatalogServiceTest {
	@Autowired
	private CatalogService catalogService;
	
	@Test
	public void qryCatalog() {
		List<CatalogRspBody> l = catalogService.qryShopCatInfo();
		System.out.println(l.toString());
		
	}
}
