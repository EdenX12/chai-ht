package com.tian.sakura.cdd.srv.web.shop.dto.coupon;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopCouponListRspBody {
	
   @ApiModelProperty("券ID")
   private String couponId;
	
   @ApiModelProperty("券状态  0-未开始 1-生效中   2-已结束 3-已作废")
   private Integer couponState;
   
   @ApiModelProperty("券面值")
   private BigDecimal couponAmount;
   
   @ApiModelProperty("券名称")
   private String couponName;
   
   @ApiModelProperty("券使用条件 0-立减 1-满减 2-超级券")
   private Integer useCon;
   
   @ApiModelProperty("券数量")
   private Integer couponQuantity;
   
   @ApiModelProperty("开始时间")
   private Long startDate;

   @ApiModelProperty("结束时间")
   private Long endDate;
   
	@ApiModelProperty("有效期天数")
	private Integer limitDays;
	
	@ApiModelProperty("适用商品 0-全部，1-指定组，2-指定商品")
	private Integer productScope;
	
	@ApiModelProperty("组id")
    private List<String> groups ;
    
	@ApiModelProperty("商品id")
    private List<String> products ;
	
	@ApiModelProperty("优惠券类型  0-立减券 1-满减券 2-超级券 3-下次购物抵扣券")
	private Integer couponType;

   
}
