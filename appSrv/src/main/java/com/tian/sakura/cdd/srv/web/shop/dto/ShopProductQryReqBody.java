package com.tian.sakura.cdd.srv.web.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopProductQryReqBody {
	@ApiModelProperty("商品Id")
	private String productId;
	
	@ApiModelProperty("上架-1，下架-3")
	private Integer productStatus;
	
}
