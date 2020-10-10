package com.tian.sakura.cdd.srv.service.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.video.service.auth.ExpressService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExpressServiceTest {
	@Autowired
	private ExpressService expressService;
	
	@Test
	public void listTest() {
		Object obj = expressService.listExpress(null);
		System.out.println(JSON.toJSONString(obj));
	}
}
