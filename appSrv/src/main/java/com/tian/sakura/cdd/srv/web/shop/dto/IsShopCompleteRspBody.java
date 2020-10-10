package com.tian.sakura.cdd.srv.web.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class IsShopCompleteRspBody {
	@ApiModelProperty("店铺id")
	private Integer shopId;
	
	@ApiModelProperty("店铺是否完善，0-没有店铺，1-店铺不完善,2-店铺已完善")
	private String flag;
}
