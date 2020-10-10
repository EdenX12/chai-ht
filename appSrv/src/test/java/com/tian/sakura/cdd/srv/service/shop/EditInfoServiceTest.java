package com.tian.sakura.cdd.srv.service.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpReq;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class EditInfoServiceTest {
	@Autowired
	private CorpService corpService;

	@Test
	public void editInfo() throws IOException {
		CorpReq req = new CorpReq();
		CorpReqBody body = new CorpReqBody();
		body.setShopId(34);
		
		String shopLogo =org.springframework.util.Base64Utils.encodeToString(
				FileUtils.readFileToByteArray(new File("d:/logo.jpg")));
		
		//body.setShopLogo(shopLogo);

		
		String weixin="sooo";
		body.setWeixin(weixin);
		String qq = "32323";
		body.setQq(qq);
		List<AddressBody> l = new ArrayList<>();
		AddressBody sr = new AddressBody();
		sr.setProvinceId(1);
		sr.setCityId(2);
		sr.setCountryId(3);
		sr.setAddress("中关村");
		sr.setProvinceName("dsdsds");
		sr.setCityName("45");
		sr.setCountryName("6g");
		l.add(sr);

		List<AddressBody> l2 = new ArrayList<>();
		AddressBody sr2 = new AddressBody();
		sr2.setProvinceId(1);
		sr2.setCityId(2);
		sr2.setCountryId(3);
		sr2.setAddress("中关村");
		sr2.setProvinceName("dsdsds");
		sr2.setCityName("45");
		sr2.setCountryName("6g");
		l2.add(sr2);
		body.setShopReturnList(l);

		body.setSelfAddressList(l2);
		String userId = "";
		//corpService.editInfo(userId,body);
		
	}
}
