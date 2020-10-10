package com.tian.sakura.cdd.srv.web.mytask.dto;

import com.tian.sakura.cdd.common.entity.BasePage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MyTaskLineReqBody extends BasePage{
	@ApiModelProperty("用户id")
	private String userId;
	@ApiModelProperty("商品id")
	private String productId;
	
}
