package com.tian.sakura.cdd.srv.service.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityQueryServiceTest {
	@Autowired
	private ActivityQueryService activityQueryService;
	@Test
    public void list() {
		activityQueryService.getAllActivity();
	}
}
