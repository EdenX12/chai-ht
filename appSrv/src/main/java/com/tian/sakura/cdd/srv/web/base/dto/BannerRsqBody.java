package com.tian.sakura.cdd.srv.web.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class BannerRsqBody {
    //banner类型
    @ApiModelProperty("banner类型，0-APP引导页,1-首页轮播图")
    private Integer bannerType;

    private String bannerUrl;

    private String bannerName;

    private String jumpUrl;
}
