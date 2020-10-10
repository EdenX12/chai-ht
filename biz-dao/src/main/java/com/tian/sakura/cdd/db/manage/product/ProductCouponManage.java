package com.tian.sakura.cdd.db.manage.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.productCoupon.ProductCouponMapper;
import com.tian.sakura.cdd.db.domain.productCoupon.ProductCoupon;

@Service
@Transactional
public class ProductCouponManage extends AbstractSingleManage<ProductCoupon, Integer> {

	@Autowired
	private ProductCouponMapper productCouponMapper;

	@Override
	protected AbstractSingleMapper<ProductCoupon, Integer> getSingleMapper() {
		// TODO Auto-generated method stub
		return productCouponMapper;
	}

	public void cancelByCouponId(Integer shopId, Integer couponId) {
		productCouponMapper.cancelByCouponId(shopId, couponId);
	}

	public List<ProductCoupon> selectByCouponId(@Param("shopId") Integer shopId, @Param("couponId") Integer couponId) {
		return productCouponMapper.selectByCouponId(shopId, couponId);
	}
}
