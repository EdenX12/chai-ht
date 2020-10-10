package com.tian.sakura.cdd.srv.web.login.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 微信登录请求体
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class WxLoginReqBody {
    @NotBlank(message = "微信授权码不能为空")
    private String code;

//    @ApiModelProperty("渠道[0-app,1-gzh,2-minip]，如果空，默认为app，")
//    @Pattern(regexp = "[123]", message = "渠道来源[1-app|2-公众号|3-小程序]")
//    private String channel;

    @ApiModelProperty("邀请码")
    private String inviteCode;

    private String deviceId;
}
