package com.tian.sakura.cdd.srv.web.shop.dto.coupon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopCouponAddReqBody {

	@ApiModelProperty("优惠券id")
	private Integer couponId;
	
	@ApiModelProperty("优惠券类型  0-立减券 1-满减券 2-超级券 3-下次购物抵扣券")
	private Integer couponType;
	
	@ApiModelProperty("优惠券面值")
	private BigDecimal couponAmount;
	
	@ApiModelProperty("使用门槛")
	private String couponName;
	
	@ApiModelProperty("最低消费金额")
	private BigDecimal minConsumeAmount;
	
	@ApiModelProperty("发放数量")
	private Integer couponQuantity;
	
	@ApiModelProperty("开始时间")
	private Date startDate;
	
	@ApiModelProperty("结束时间")
	private Date endDate;

	@ApiModelProperty("有效期天数")
	private Integer limitDays;
	
	@ApiModelProperty("适用产品类型 0-全部 ，1-指定组，2-指定商品")
	private Integer productScope;
	
	@ApiModelProperty("商品列表")
	private List<String> products;
	
	@ApiModelProperty("组列表")
	private List<String> groups;

}
