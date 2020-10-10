package com.tian.sakura.cdd.srv.web.task.dto;

import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 产品分享请求体
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class ShareNoticeReqBody {

    @NotNull(message = "分享状态不能为空")
    //@Pattern(regexp = "[12]", message = "分享状态1-成功；2-失败")
    @CustEnumVal(value = "1,2", message = "分享状态1-成功；2-失败")
    @ApiModelProperty("分享状态1-成功；2-失败")
    private Integer shareStatus;

    @NotBlank(message = "分享标识不能为空")
    @ApiModelProperty("分享标识")
    private String shareId;

    @NotBlank(message = "商品标识不能为空")
    @ApiModelProperty("商品标识")
    private String productId;


}
