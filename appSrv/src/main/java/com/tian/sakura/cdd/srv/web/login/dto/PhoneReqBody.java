package com.tian.sakura.cdd.srv.web.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 基于手机+验证码请求体
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class PhoneReqBody {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "\\d{11}$",
            message = "请输入正确格式的手机号")
    @ApiModelProperty("手机号")
    private String phone;

    @NotBlank(message = "短信验证码不能为空")
    @Pattern(regexp = "\\d{6}$",
            message = "请输入6位数字类型的短信验证码")
    @ApiModelProperty("动态码")
    private String smsCode;

    @NotBlank(message = "摘要不能为空")
    @ApiModelProperty("摘要 md5(phone+smsCode+'login/cdd'")
    private String digest;

    // @NotBlank(message = "设备标识不能为空")
    @ApiModelProperty("设备标识")
    private String deviceId;
}
