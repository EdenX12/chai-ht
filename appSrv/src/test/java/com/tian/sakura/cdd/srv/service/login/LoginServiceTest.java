package com.tian.sakura.cdd.srv.service.login;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.common.web.RequestHead;
import com.tian.sakura.cdd.db.domain.user.UserLoginLog;
import com.tian.sakura.cdd.db.manage.user.UserLoginLogManage;
import com.tian.sakura.cdd.srv.web.login.dto.*;
import com.tian.sakura.cdd.wx.message.WxUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.regex.Pattern;

import static com.tian.sakura.cdd.common.constants.SrvConstants.MD5_SALTY_LOGIN;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserLoginLogManage userLoginLogManage;

    @Test
    public void loginLog() {
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setId(IdGenUtil.uuid());
        userLoginLog.setLoginTime(new Date());
        userLoginLogManage.insert(userLoginLog);
    }

    public static void main(String[] args) {
        String pa = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$";
        Pattern pattern = Pattern.compile(pa);
        System.out.println(pattern.matcher("aA12345678").matches());
    }

    @Test
    public void loginByPhone() {

        PhoneLoginReq req = new PhoneLoginReq();
        RequestHead head = new RequestHead();
        // 0-app
        head.setChannel(0);
        req.setHead(head);

        PhoneLoginReqBody body = new PhoneLoginReqBody();
        body.setPhone("15900955396");
        body.setSmsCode("123456");
        //body.setInviteCode("DP65");
        StringBuilder loginDi = new StringBuilder().append(body.getPhone())
                .append(body.getSmsCode())
                .append(MD5_SALTY_LOGIN);
        String loginDigest = MD5Util.md5Hex(loginDi.toString());
        body.setDigest(loginDigest);
        req.setBody(body);

        System.out.println(JSON.toJSONString(loginService.loginByPhone(req)));
    }

    @Test
    public  void loginWx() {
        WxLoginReq req = new WxLoginReq();
        RequestHead head = new RequestHead();
        // 0-app
        head.setChannel(0);
        WxLoginReqBody body = new WxLoginReqBody();
        body.setCode("codexxx");
        req.setBody(body);
        req.setHead(head);
        WxUser wxUser = new WxUser();
        wxUser.setUnionid("test-unionid-0421001");
        wxUser.setOpenid("test-openid-0421002");
        System.out.println(JSON.toJSONString(loginService.loginByWx(req, wxUser)));

    }

    @Test
    public void phoneBindWeChat() {
        PhoneBindWeChatReq req = new PhoneBindWeChatReq();
        RequestHead head = new RequestHead();
        // 0-app
        head.setChannel(0);

        PhoneBindWeChatReqBody body = new PhoneBindWeChatReqBody();

        body.setPhone("15900955394");
        body.setSmsCode("123456");
        body.setOpenId("test-openid-0421002");
        body.setUnionId("test-unionid-0421001");

        StringBuilder sb = new StringBuilder().append(body.getPhone()).append(body.getSmsCode()).append(MD5_SALTY_LOGIN);
        String validateDigest = MD5Util.md5Hex(sb.toString());
        body.setDigest(validateDigest);
        req.setBody(body);
        req.setHead(head);
        loginService.bindPhone(req);
    }
}
