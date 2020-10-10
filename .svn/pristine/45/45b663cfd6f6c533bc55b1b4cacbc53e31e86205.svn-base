package com.tian.sakura.cdd.srv.service.sms;

import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.srv.service.base.SmsCodeService;
import com.tian.sakura.cdd.srv.web.base.dto.SmsCodeApplyReq;
import com.tian.sakura.cdd.srv.web.base.dto.SmsCodeApplyReqBody;
import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tian.sakura.cdd.common.constants.SrvConstants.MD5_SALTY;

/**
 * 测试
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsCodeServiceTest {

    @Autowired
    private SmsCodeService smsCodeService;

    @Test
    public void obtainSmsCode() {
        SmsCodeApplyReq request = new SmsCodeApplyReq();
        SmsCodeApplyReqBody reqBody = new SmsCodeApplyReqBody();
        reqBody.setPhone("15900955391");
        reqBody.setDigest(MD5Util.md5Hex(reqBody.getPhone().concat(MD5_SALTY)));

        request.setBody(reqBody);
        smsCodeService.obtainSmsCode(request);
    }

    @Test
    public void validateSmsCode() {

        PhoneReqBody body = new PhoneReqBody();
        body.setPhone("15900955391");
        body.setSmsCode("322471");

        StringBuilder sb = new StringBuilder(body.getPhone()).append(body.getSmsCode()).append(MD5_SALTY);
        String digestApp = MD5Util.md5Hex(sb.toString());
        body.setDigest(digestApp);

        smsCodeService.validateSmsCode(body);
    }
}
