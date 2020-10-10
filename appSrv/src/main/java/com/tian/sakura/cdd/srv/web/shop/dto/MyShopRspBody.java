package com.tian.sakura.cdd.srv.web.shop.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MyShopRspBody {
	@ApiModelProperty("账户可用余额")
	private BigDecimal balance;
	
	@ApiModelProperty("账户冻结余额")
	private BigDecimal frozenBalance;
	
	@ApiModelProperty("待付款订单数量")
	private Integer toBePayCnt;
	
	@ApiModelProperty("待发货订单数量")
	private Integer toBeSendCnt;
	
	@ApiModelProperty("已发货订单数量")
	private Integer hasSentCnt;
	
	@ApiModelProperty("售后中订单数量")
	private Integer applyBackCnt;
}
