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

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpReq;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualReq;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.IsShopCompleteRspBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CorpServiceTest {
	@Autowired
	private CorpService corpService;
	
	@Test
	public void insertCorpInfo() {
		CorpReq req = new CorpReq();
		CorpReqBody body = new CorpReqBody();
		
		body.setAddress("世博源");
		body.setCallCenter("021-888888");
		body.setCity(2);
		body.setCountry(2);
		body.setEmail("haiguangliu@aliyun.com");
		body.setIndividualName("刘海光");
		body.setMobile("18621687366");
		body.setProvince(2);
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(10);
		l.add(15);
		body.setShopCatlog(l);
		body.setShopName("测试店铺1002");
		req.setBody(body);
		String userId = "1008";

		//corpService.corpBasicInfo(userId, body);
	}
	
//	@Test
//	public void updateCorpIdCard() throws IOException {
//		CorpReq req =new CorpReq();
//		CorpReqBody body = new CorpReqBody();
//		body.setShopId(17);
//		String front =org.springframework.util.Base64Utils.encodeToString(
//				FileUtils.readFileToByteArray(new File("d:/logo.jpg")));
//		
//		
//		body.setIdCardFront(front);
//
//		String back = org.springframework.util.Base64Utils.encodeToString(
//				FileUtils.readFileToByteArray(new File("d:/100.png")));
//		
//		org.springframework.util.Base64Utils.decodeFromString(back);
//		body.setIdCardBack(back);
//		
//		String license = org.springframework.util.Base64Utils.encodeToString(
//				FileUtils.readFileToByteArray(new File("d:/100.png")));
//		
//		org.springframework.util.Base64Utils.decodeFromString(license);
//		body.setLicense(license);
//		
//		body.setIdCardNo("210726197110301713");
//		body.setIdCardExpire("2030-01-01");
//		body.setLicenseNo("31099999");
//		body.setCorpName("上海水德");
//		req.setBody(body);
//		corpService.corpCardInfo(body);
//
//	}
	
	@Test
	public void isComplete() {
		SUser user = new SUser();
		user.setId("3dc4db16e90848e18bcb91553f35eff7");
		IsShopCompleteRspBody rsp = corpService.isShopComplete(user);
		System.out.println(rsp.toString());
	}
	
}
