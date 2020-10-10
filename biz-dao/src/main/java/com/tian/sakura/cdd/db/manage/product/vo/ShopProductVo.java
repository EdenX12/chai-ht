package com.tian.sakura.cdd.db.manage.product.vo;

import java.math.BigDecimal;
import lombok.Data;


@Data
public class ShopProductVo {
    //商品Id
	private String productId;

	//商品名称
	private String productName;

	//商品状态
	private String productStatus;
	
	//商品库存
	private BigDecimal stockQuantity;

	//商品销量
	private BigDecimal saleQuantity;

	//商品折后价格
	private BigDecimal productPrice;
	
	//商品原价
	private BigDecimal scribingPrice;
	
	//商品缩略图
	private String productImg;

}
