package com.tian.sakura.cdd.srv.web.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 产品分享请求体
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class SharePrdReqBody {

    @NotBlank(message = "商品标识不能为空")
    @ApiModelProperty("商品标识")
    private String productId;

    @ApiModelProperty("父级分享标识")
    private String parentShareId;

    @ApiModelProperty("分享类型 1-产品界面分享； 2-任务界面分享")
    private String shareType;

}
