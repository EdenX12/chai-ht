package com.tian.sakura.cdd.batch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskOrderCloseServiceTest {

    @Autowired
    private TaskOrderCloseService taskOrderCloseService;

    @Test
    public void closeorder() {
        taskOrderCloseService.closeTaskOrder();
    }
}
