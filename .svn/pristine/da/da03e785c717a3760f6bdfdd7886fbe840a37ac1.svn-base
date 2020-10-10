package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.web.user.dto.AmountLogQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.AmountLogQueryReqBody;
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
public class UserApiServiceTest {

    @Autowired
    private UserApiService userApiService;

    @Autowired
    private SUserManage userManage;

    private SUser user;
    @Before
    public void init() {
        //userManage.selectByPrimary("123");
        user = new SUser();
        user.setId("786c55e92d034a67b54bb42037e6f79c");
        user.setUserPhone("15900955391");
    }

    @Test
    public void updateNickName() {
        userApiService.updateNickName("nickName", "59fbae33b345412a92c3004a72e52649");
    }

    @Test
    public void selectById() {
        userApiService.refresh(user);
    }

    @Test
    public void queryAmountLog() {
        AmountLogQueryReq req = new AmountLogQueryReq();
        AmountLogQueryReqBody body = new AmountLogQueryReqBody();
        body.setChangeType(5);
        body.setAmtDirect(0);
        req.setBody(body);
        userApiService.queryAmountLog(user, req);
    }
}
