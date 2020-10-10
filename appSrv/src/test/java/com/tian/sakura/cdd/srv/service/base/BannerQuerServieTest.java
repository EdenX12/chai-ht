package com.tian.sakura.cdd.srv.service.base;

import com.tian.sakura.cdd.srv.web.base.dto.BannerReq;
import com.tian.sakura.cdd.srv.web.base.dto.BannerReqBody;
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
public class BannerQuerServieTest {

    @Autowired
    private BannerQueryService bannerQueryService;


    @Test
    public void list() {
        BannerReq bannerReq = new BannerReq();

        BannerReqBody body = new BannerReqBody();
        body.setBannerType(1);
        bannerReq.setBody(body);
        bannerQueryService.listUsableBanner(bannerReq);
    }
}
