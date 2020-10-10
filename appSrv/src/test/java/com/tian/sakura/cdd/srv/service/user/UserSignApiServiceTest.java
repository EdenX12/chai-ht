package com.tian.sakura.cdd.srv.service.user;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.user.SUser;
import org.junit.Before;
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
public class UserSignApiServiceTest {

    @Autowired
    private UserSignApiService userSignApiService;

    private SUser user;
    @Before
    public void init() {
        //userManage.selectByPrimary("123");
        user = new SUser();
        user.setId("786c55e92d034a67b54bb42037e6f79c");
        user.setUserPhone("15900955391");
    }

    @Test
    public void signWeekList() {
        System.out.println(JSON.toJSONString(userSignApiService.signWeekList(user)));
    }

    @Test
    public void sign() {
        userSignApiService.sign(user);
    }
}
