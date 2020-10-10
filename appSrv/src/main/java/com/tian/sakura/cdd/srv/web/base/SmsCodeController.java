package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.cdd.srv.service.base.SmsCodeService;
import com.tian.sakura.cdd.srv.web.base.dto.SmsCodeApplyReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证码api
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("sms")
@Api(value="短信验证码", tags = {"验证码"})
public class SmsCodeController {

    @Autowired
    private SmsCodeService smsCodeService;
    @Value("${trade.switch.sms}")
    private boolean printSms;


    @ApiOperation(value="发送验证码" , notes = "")
    @PostMapping(value = "send", produces = {"application/json"})
    public Map<String,String> sendSmsCode(@RequestBody @Validated SmsCodeApplyReq smsCodeApplyReq) {
        Map<String,String> result = new HashMap<>();
        String smsCode = smsCodeService.obtainSmsCode(smsCodeApplyReq);
        if (printSms) {
           result.put("smsCode", smsCode);
        }
        return result;
    }
}
