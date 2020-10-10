package com.tian.sakura.cdd.srv.web.base.dto;

import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class SmsCodeApplyReqBody {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "\\d{11}$",
            message = "请输入正确格式的手机号")
    @ApiModelProperty("手机号")
    private String phone;

    @NotBlank(message = "摘要不能为空")
    @ApiModelProperty("摘要，md5(phone+'CDD')")
    private String digest;

    // @NotBlank(message = "设备标识不能为空")
    @ApiModelProperty("设备标识")
    private String deviceId;
}
