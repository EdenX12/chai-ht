package com.tian.sakura.cdd.srv.web.login.dto;


import com.tian.sakura.cdd.common.web.RequestHead;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class LoginReqBody {

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "密码或验证码不能为空")
    private String password;

    private String smsCode;

    private String deviceId;
}
