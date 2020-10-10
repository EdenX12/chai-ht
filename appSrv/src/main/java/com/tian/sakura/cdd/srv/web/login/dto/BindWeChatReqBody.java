package com.tian.sakura.cdd.srv.web.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@ApiModel
public class BindWeChatReqBody extends PhoneReqBody{
    @NotBlank(message = "微信授权码不能为空")
    @ApiModelProperty("授权码")
    private String code;

    //private String flag;
}
