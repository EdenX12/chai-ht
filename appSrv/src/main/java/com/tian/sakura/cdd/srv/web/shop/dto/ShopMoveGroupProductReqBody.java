package com.tian.sakura.cdd.srv.web.shop.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ShopMoveGroupProductReqBody {
	@ApiModelProperty("源组id")
	private Integer sourceGroupId;
	
	@ApiModelProperty("目的组id")
	private Integer targetGroupId;
	
	@ApiModelProperty("商品id")
	List<String> productIds;
}
