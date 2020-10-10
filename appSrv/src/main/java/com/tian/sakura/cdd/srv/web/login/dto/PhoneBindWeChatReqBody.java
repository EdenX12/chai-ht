package com.tian.sakura.cdd.srv.web.login.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PhoneBindWeChatReqBody extends PhoneReqBody{
    @ApiModelProperty("微信openid")
    private String openId;
    @ApiModelProperty("微信unionid")
    private String unionId;

    @ApiModelProperty("邀请码")
    private String inviteCode;
    //private String flag;
}
