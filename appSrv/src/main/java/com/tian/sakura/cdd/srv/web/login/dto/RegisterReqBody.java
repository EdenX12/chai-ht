package com.tian.sakura.cdd.srv.web.login.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
public class RegisterReqBody {

    @NotBlank(message = "登录账号不能为空")
    private String loginNo;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$",
            message = "密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符")
    private String password;
    @NotBlank(message = "密码不能为空")
    private String confirmPwd;
}
