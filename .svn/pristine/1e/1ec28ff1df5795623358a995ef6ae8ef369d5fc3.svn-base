package com.tian.sakura.cdd.srv;

import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;

import java.util.Arrays;
import java.util.Date;

import static com.tian.sakura.cdd.common.constants.SrvConstants.MD5_SALTY;
import static com.tian.sakura.cdd.common.constants.SrvConstants.MD5_SALTY_LOGIN;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class SrvMainTest {

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        phoneloginDegist("15900955395", "440430");
        //phoneRegisterDegist("15900955392","883852");
        smsDegist("15900955393");

        //登录摘要


        //testUserType();
    }

    private static void phoneloginDegist(String phone, String smsCode) {
        StringBuilder loginDi = new StringBuilder().append(phone).append(smsCode).append(MD5_SALTY_LOGIN);
        String loginDigest = MD5Util.md5Hex(loginDi.toString());
        System.out.println("login>>" + loginDigest);
    }


    private static void phoneRegisterDegist(String phone, String smsCode) {
        PhoneReqBody body = new PhoneReqBody();
        body.setPhone(phone);
        body.setSmsCode(smsCode);

        StringBuilder sb = new StringBuilder(body.getPhone()).append(body.getSmsCode()).append(MD5_SALTY_LOGIN);

        System.out.println("注册>>" + MD5Util.md5Hex(sb.toString()));
    }

    private static void smsDegist(String phone) {
        //发送验证码摘要
        String digestApp = MD5Util.md5Hex(phone.concat(MD5_SALTY));
        System.out.println("sms>>>" + digestApp);
    }

    private static void testUserType() {
        EUserType[] userTypes = new EUserType[]{EUserType.BUSINESS};
        System.out.println(Arrays.deepToString(userTypes));
      //  EUserType[] userTypes = new EUserType[]{EUserType.BOTH, EUserType.BUSINESS};
       // System.out.println(Arrays.deepToString(userTypes));
    }
}
