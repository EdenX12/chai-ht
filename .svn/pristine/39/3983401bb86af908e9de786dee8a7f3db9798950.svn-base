package com.tian.sakura.cdd.srv.service.share;

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.task.ShareService;
import com.tian.sakura.cdd.srv.web.task.dto.ShareNoticeReq;
import com.tian.sakura.cdd.srv.web.task.dto.ShareNoticeReqBody;
import com.tian.sakura.cdd.srv.web.task.dto.SharePrdReq;
import com.tian.sakura.cdd.srv.web.task.dto.SharePrdReqBody;
import com.tian.sakura.cdd.wx.message.WxUser;
import com.tian.sakura.video.service.core.QRCodeCore;
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
public class ShareServiceTest {

    @Autowired
    private ShareService shareService;

    @Before
    public void init() {
        SUser user = new SUser();
        user.setId("f372ebb1a9634bd9996272f239fce7c2");
        LoginUserThreadLocal.putLoginUser(user);
    }

    @Test
    public void shareProdcut() {
        SharePrdReq req = new SharePrdReq();
        SharePrdReqBody body = new SharePrdReqBody();
        body.setProductId("11");
        body.setShareType("1");
        body.setParentShareId("");

        req.setBody(body);
        String shareId = shareService.shareProduct(req);
        System.out.println(shareId);
    }

    @Test
    public void notice() {
        ShareNoticeReq req = new ShareNoticeReq();
        ShareNoticeReqBody body = new ShareNoticeReqBody();
        body.setProductId("11");
        body.setShareId("e1e880ce351845dfa736fd14d412387b");
        body.setShareStatus(1);
        req.setBody(body);
        shareService.notice(req);

    }
    
    @Test
    public void shareBtnClick() {
    	SharePrdReq req = new SharePrdReq();
    	SharePrdReqBody body =  new SharePrdReqBody();
    	body.setProductId("10");
    	req.setBody(body);
    	String unionId = "oNiZGt1gneKKjqDF_Pju2nRGS-30";
    	shareService.shareBtnClick(req,unionId);
    	
    }

    @Test
    public void trackShareRaletion() {
        WxUser wxUser = new WxUser();
        wxUser.setUnionid("test-unionid001");
        wxUser.setOpenid("test-openid001");
        String shareId = "e1e880ce351845dfa736fd14d412387b";
        shareService.trackShareRaletion(wxUser, shareId);
    }

    @Test
    public void upgradeRelation() {
        String userId = "test";
        shareService.upgradeRelation(userId);
    }
    @Autowired
    private QRCodeCore qrCode;
    @Test
    public void qrcode() {
        String url = qrCode.encode("https://www.baidu.com");

    }
}
