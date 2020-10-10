package com.tian.sakura.cdd.srv.web.shop.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopProductReqBody {
	
	@ApiModelProperty("商品ID")
	private String productId;
	
	@ApiModelProperty("商品名称")
	private String productName;
	
	@ApiModelProperty("一级分类ID")
	private String productType1Id;
	
	@ApiModelProperty("二级分类ID")
	private String productType2Id;
	
	@ApiModelProperty("分组ID")
	private Integer groupId;
	
	//物流方式主要四类：1线下自提（输入自提地址）、2商家送货（设置送货费用，可零）、3快递包邮（也就是物流费是零）、4快递收费（设置固定金额）。
	@ApiModelProperty("配送方式,1线下自提，2商家送货，3快递包邮，4快递收费")
	private Integer deliveryType;
	
	@ApiModelProperty("运费")
	private BigDecimal freight;
	
	@ApiModelProperty("商品售价")
	private BigDecimal price;
	
	@ApiModelProperty("折扣比例")
	private BigDecimal discountRate;
	
	@ApiModelProperty("折后价格")
	private BigDecimal discountPrice;
	
	@ApiModelProperty("库存数量")
	private Integer quantity;
	
	@ApiModelProperty("售卖时间")
	private Date startDate;
	
}
