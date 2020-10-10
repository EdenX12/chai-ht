package com.tian.sakura.cdd.srv.web.login;

import com.tian.sakura.cdd.srv.service.login.RegisterService;
import com.tian.sakura.cdd.srv.web.login.dto.SmsRegisterReq;
import com.tian.sakura.cdd.srv.web.login.dto.WxRegisterReqBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册api
 *  <p> 注册和登录合并到一起</p>
 *
 * @author lvzonggang
 */
@Deprecated
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 手机号 + 短信验证码登录
     * @param registerReq
     */
    @PostMapping("sms")
    @ApiOperation(value = "手机号 + 短信验证码注册")
    public void registerBySms(@RequestBody @Validated SmsRegisterReq registerReq) {
        registerService.registerAccount(registerReq);
    }

    @PostMapping("wx")
    public void registerByWx(@RequestBody @Validated WxRegisterReqBody registerReq) {
        //registerService.registerAccount(registerReq);
    }
}
