package com.tian.sakura.cdd.db.manage.shop.vo;

import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;
import com.tian.sakura.cdd.db.manage.product.vo.ProductRecommendQueryVo;
import com.tian.sakura.cdd.db.manage.product.vo.ProductRecommendQueryVo.ProductRecommendQueryVoBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ShopGroupVo extends ShopGroup{
	//每组中商品数量
	private int productCnt;
}
