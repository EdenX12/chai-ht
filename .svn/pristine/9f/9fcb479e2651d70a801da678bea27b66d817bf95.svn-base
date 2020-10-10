package com.tian.sakura.cdd.db.manage.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.productReviewStat.ProductReviewStatMapper;
import com.tian.sakura.cdd.db.domain.productReviewStat.ProductReviewStat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductReviewStatManage extends AbstractSingleManage<ProductReviewStat,String>{
    @Autowired
	private ProductReviewStatMapper productReviewStatMapper; 
	@Override
	protected AbstractSingleMapper<ProductReviewStat, String> getSingleMapper() {
		
		return productReviewStatMapper;
	}
	//该商品关注人数
	public int totalFocus(String productId) {
		Integer cnt = productReviewStatMapper.totalFocus(productId);

		return cnt != null ? cnt : 0;
	}

	public ProductReviewStat selectByProductId(String productId) {
		return productReviewStatMapper.selectByProductId(productId);
	}

	public void increase(String productId) {
		productReviewStatMapper.increase(productId);
	}

	public void decrease(String productId) {
		productReviewStatMapper.decrease(productId);
	}

}
