package com.tian.sakura.cdd.srv.web.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author lvzonggang
 */


@Setter
@Getter
@ApiModel
public class JpushRegReqBody {
    @ApiModelProperty("设备id")
    @NotBlank(message = "设备id不能为空")
    private String deviceId;
    @ApiModelProperty("极光注册id")
    @NotBlank(message="极光注册id不能为空")
    private String registrationId;
}
