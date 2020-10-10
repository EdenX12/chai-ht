package com.tian.sakura.cdd.srv.web.shop.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ShopDeleteGroupProductReqBody {
	@ApiModelProperty("分组id")
	private Integer groupId;
	@ApiModelProperty("组内商品id")
	private List<String> productIds;
}
