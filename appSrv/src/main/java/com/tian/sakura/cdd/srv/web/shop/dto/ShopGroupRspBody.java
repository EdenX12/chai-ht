package com.tian.sakura.cdd.srv.web.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ShopGroupRspBody {
	@ApiModelProperty("店铺ID")
	private Integer shopId;
	
	@ApiModelProperty("分组ID")
	private Integer groupId;
	
	@ApiModelProperty("分组名称")
	private String  groupName;
	
	@ApiModelProperty("组内商品数量")
	private int     prdCnt;
}
