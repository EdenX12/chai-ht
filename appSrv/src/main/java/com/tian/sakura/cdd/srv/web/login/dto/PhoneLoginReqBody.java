package com.tian.sakura.cdd.srv.web.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 手机号+短信验证码登录请求体
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class PhoneLoginReqBody extends PhoneReqBody {

    @ApiModelProperty("邀请码")
    private String inviteCode;
}
