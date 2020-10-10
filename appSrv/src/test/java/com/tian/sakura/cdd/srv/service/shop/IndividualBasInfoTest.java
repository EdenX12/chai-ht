package com.tian.sakura.cdd.srv.service.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.tian.sakura.cdd.common.util.Base64Utils;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualReq;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndividualBasInfoTest {
	@Autowired
	private IndividualService individualService;
	@Test
	public void insertIndividualBasicInfo() {
		IndividualReq req =new IndividualReq();
		IndividualReqBody body = new IndividualReqBody();
		body.setAddress("徐家汇");
		body.setCallCenter("021-888888");
		body.setCity(1);
		body.setCountry(1);
		body.setEmail("haiguangliu@aliyun.com");
		body.setIndividualName("刘海光");
		body.setMobile("18621687366");
		body.setProvince(20);
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(10);
		l.add(15);
		body.setShopCatlog(l);
		body.setShopName("测试店铺0510");
		req.setBody(body);
		String userId = "2003";
		
		//individualService.individualBasicInfo(userId, body);
		
	}

//	@Test
//	public void updateIndividualIdCard() throws IOException {
//		IndividualReq req =new IndividualReq();
//		IndividualReqBody body = new IndividualReqBody();
//		body.setShopId(15);
//		String front =org.springframework.util.Base64Utils.encodeToString(
//				FileUtils.readFileToByteArray(new File("d:/logo.jpg")));
//		org.springframework.util.Base64Utils.decodeFromString(front);
//		
//		body.setIdCardFront(front);
//
//		String back = org.springframework.util.Base64Utils.encodeToString(
//				FileUtils.readFileToByteArray(new File("d:/100.png")));
//		
//		org.springframework.util.Base64Utils.decodeFromString(back);
//		body.setIdCardBack(back);
//		
//		body.setIdCardNo("210726197110301713");
//		body.setIdCardExpire("2030-01-01");
//		req.setBody(body);
//		individualService.individualIdCardInfo(body);
//		
//	}

}
