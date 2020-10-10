package com.tian.sakura.cdd.srv.web.shop.dto;

import com.tian.sakura.cdd.common.entity.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopReqBody extends BasePage{
	@ApiModelProperty("商铺id,如果未空则取登录人的商铺")
	private Integer shopId;
	
	@ApiModelProperty("分组id")
	private Integer groupId;
	
	@ApiModelProperty("商品名称模糊查询")
	private String productName ;
	

}
