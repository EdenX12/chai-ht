package com.tian.sakura.cdd.db.dao.productReviewStat;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.productReviewStat.ProductReviewStat;

public interface ProductReviewStatMapper extends AbstractSingleMapper<ProductReviewStat, String> {
	//该商品关注人数
	Integer totalFocus(String productId);

	ProductReviewStat selectByProductId(String productId);

	int increase(String productId);

	int decrease(String productId);


}