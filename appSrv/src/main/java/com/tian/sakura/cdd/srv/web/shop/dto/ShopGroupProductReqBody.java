package com.tian.sakura.cdd.srv.web.shop.dto;

import com.tian.sakura.cdd.common.entity.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopGroupProductReqBody extends BasePage{
	@ApiModelProperty("商铺id")
	private Integer shopId;

	@ApiModelProperty("分组id")
	private Integer groupId;
}
