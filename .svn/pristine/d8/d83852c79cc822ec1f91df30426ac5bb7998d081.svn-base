package com.tian.sakura.video.service.core;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.tian.sakura.cdd.common.constants.SrvConstants;
import com.tian.sakura.cdd.common.util.SendSmsUtils;
import com.tian.sakura.video.service.auth.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendSmsCore {

    @Autowired
    private ParamService paramService;

    public SendSmsResponse sendSms(String phone, String code) {
        return SendSmsUtils.sendSms(phone, code,
                paramService.getValueByKey(SrvConstants.SMS_TEMPLATE_CODE),
                paramService.getValueByKey(SrvConstants.ALI_ACCESS_KEY_ID),
                paramService.getValueByKey(SrvConstants.ALI_ACCESS_KEY_SECRET),
                paramService.getValueByKey(SrvConstants.SMS_SIGN_NAME));
    }
}
