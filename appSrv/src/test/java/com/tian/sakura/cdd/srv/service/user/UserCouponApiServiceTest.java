package com.tian.sakura.cdd.srv.service.user;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.web.user.dto.UserCouponQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.UserCouponQueryReqBody;
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
public class UserCouponApiServiceTest {

    @Autowired
    private UserCouponApiService userCouponApiService;



    @Test
    public void list() {
        String userId = "59fbae33b345412a92c3004a72e52649";
        UserCouponQueryReq req = new UserCouponQueryReq();
        UserCouponQueryReqBody body = new UserCouponQueryReqBody();
        body.setCouponStatus(0);
        body.setCouponType(1);
        req.setBody(body);
        System.out.println(JSON.toJSONString(userCouponApiService.list(userId,req)));
    }


}
