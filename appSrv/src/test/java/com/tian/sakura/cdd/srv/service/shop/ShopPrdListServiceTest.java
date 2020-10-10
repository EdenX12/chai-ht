package com.tian.sakura.cdd.srv.service.shop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListRspBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ShopPrdListServiceTest {
	@Autowired
	private ShopProductService shopProductService;
	@Test
	public void list() {
	
		String userId="3dc4db16e90848e18bcb91553f35eff7";
		ShopProductListReqBody reqBody =new ShopProductListReqBody();
		reqBody.setProductStatus(0);
		List<ShopProductListRspBody> l =shopProductService.productList(userId, reqBody);
		
		System.out.println(l);
}
}
