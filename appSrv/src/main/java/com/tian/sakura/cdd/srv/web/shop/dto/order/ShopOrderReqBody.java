package com.tian.sakura.cdd.srv.web.shop.dto.order;

import com.tian.sakura.cdd.common.entity.BasePage;
import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopOrderReqBody  extends BasePage{
	@ApiModelProperty("查询时间段类型，0-今天，1-昨天，2-近7天，3-近30天")
	private Integer qryDurationType;
	@ApiModelProperty("店铺Id，如果不传入则取当前登录用户的店铺")
	private Integer shopId;
	@ApiModelProperty("订单状态：0-待付款;1-待发货;2-已发货;3-售后中;4-已完成;5-待取货")
    @CustEnumVal(value = "0,1,2,3,4,5", message = "订单状态参数错误")
    private String orderStatus;

	@ApiModelProperty("模糊查询-订单编号")
	private String  orderSn;

	@ApiModelProperty("模糊查询-收货人姓名")
	private String  addressName;

	@ApiModelProperty("模糊查询-收货人手机号")
	private String  addressPhone;

}
