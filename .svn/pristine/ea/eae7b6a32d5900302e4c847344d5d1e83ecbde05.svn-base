package com.tian.sakura.cdd.srv.service.shop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tian.sakura.cdd.db.domain.productGroup.ProductGroup;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductGroupReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupRspBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ProductGroupServiceTest {
	@Autowired
	private ShopGroupService shopGroupService;
	
	@Test
	public void createPrdGroup() {
		ProductGroupReqBody body = new ProductGroupReqBody();
		String userId="555";
		Integer groupId = 3;
		List<String> prdIds =new ArrayList<>();
		prdIds.add("1");
		prdIds.add("2");
		prdIds.add("3");
		prdIds.add("5");
		body.setGroupId(groupId);
		body.setProductIds(prdIds);
		
		shopGroupService.createPrdGroup(userId, body);
	}
	@Test
	public void qryShopGroup() {
		String userId = "555";
		List<ShopGroupRspBody > l = shopGroupService.qryShopGroup(userId);
		
		System.out.println(l);
	}
}
