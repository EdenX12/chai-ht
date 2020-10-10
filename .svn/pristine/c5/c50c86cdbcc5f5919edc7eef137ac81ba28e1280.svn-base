package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class UserAddressRspBody {
    @ApiModelProperty("标识")
    private String id;
    @ApiModelProperty("真实名称")
    @NotBlank(message="真实名称不能为空")
    private String trueName;
    @ApiModelProperty("省份")
    private Integer provinceId;
    @NotBlank(message="省份不能为空")
    @ApiModelProperty("省份")
    private String provinceName;
    @ApiModelProperty("城市")
    private Integer cityId;

    @ApiModelProperty("城市")
    @NotBlank(message="城市不能为空")
    private String cityName;
    @ApiModelProperty("地区")
    private Integer areaId;
    @ApiModelProperty("地区")
    @NotBlank(message="地区不能为空")
    private String areaName;
    @ApiModelProperty("详细地区")
    @NotBlank(message="详细地址不能为空")
    private String areaInfo;
    @NotBlank(message="手机不能为空")
    @ApiModelProperty("手机")
    private String mobPhone;
    @ApiModelProperty("是否默认 true-默认")
    private Boolean defaultFlag;
    @ApiModelProperty("邮政编码")
    private Integer zipCode;
}
