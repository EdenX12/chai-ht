package com.tian.sakura.cdd.srv.web.product.dto;

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
public class ProductDetailQueryReqBody {

    @ApiModelProperty("产品id")
    @NotBlank(message = "产品id不能为空")
    private String productId;

    @ApiModelProperty("微信授权code")
    private String code;
    @ApiModelProperty("分享标识")
    private String shareId;

}
