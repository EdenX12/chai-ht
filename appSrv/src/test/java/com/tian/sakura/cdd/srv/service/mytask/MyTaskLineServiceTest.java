package com.tian.sakura.cdd.srv.service.mytask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTaskLineServiceTest {
	@Autowired
	private MyTaskService taskService;
	
	@Test
	public void list() {
		String userId="a9e6b574f88644bc96eb1bd3064430ef";
		//Object obj = taskService.qryTaskLineByUserIdPrd(userId);
		//System.out.println("我的任务线，"+JSON.toJSON(obj));
	}
}
