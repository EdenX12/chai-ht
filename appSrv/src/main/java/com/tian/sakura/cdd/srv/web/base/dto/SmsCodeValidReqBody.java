package com.tian.sakura.cdd.srv.web.base.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author lvzonggang
 * @Date 2019/8/17
 */
public class SmsCodeValidReqBody {

    @Setter
    @Getter
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @Setter
    @Getter
    private String smsType;
    @Setter
    @Getter
    private String deviceId;
    @Setter
    @Getter
    @NotBlank(message = "验证码不能为空")
    private String smsCode;
}
