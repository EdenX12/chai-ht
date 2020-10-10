package com.tian.sakura.cdd.srv.web.product.dto;

import java.util.List;

import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 进行中我的任务
 * @author liuhg
 *
 */
@Data
@ApiModel
public class MyTaskRspBody extends ProductRspBody{
	@ApiModelProperty("用户ID")
	private String userId;
    @ApiModelProperty("指定用户的总任务线数量")
    private Integer userTotalTaskLine;
    @ApiModelProperty("指定用户的正在进行中/结算中的任务线数量")
    private Integer userTotalTaskingLine;
}
