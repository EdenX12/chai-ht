package com.tian.sakura.cdd.srv.web.shop.dto.data;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopTodayDataRspBody {
	@ApiModelProperty("订单数量")
	private Integer orderTotalCnt;

	@ApiModelProperty("营业额")
	private BigDecimal orderTotalAmount;

	@ApiModelProperty("订单实付金额")
	private BigDecimal orderPayAmount;

	@ApiModelProperty("访客量")
	private Integer focusTotalCnt;

	@ApiModelProperty("待发货数量")
	private Integer toBeDeliveryTotalCnt;
	
	@ApiModelProperty("退款数量")
	private Integer returnMoneyTotalCnt;
	
	@ApiModelProperty("拆单数")
	private Integer taskTotalCnt;

}
