package com.tian.sakura.cdd.srv.web.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 手机号校验请求体
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class PhoneVerifyReqBody {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("设备id")
    private String deviceId;
}
