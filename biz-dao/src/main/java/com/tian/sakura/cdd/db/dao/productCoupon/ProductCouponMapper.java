package com.tian.sakura.cdd.db.dao.productCoupon;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.productCoupon.ProductCoupon;

public interface ProductCouponMapper extends AbstractSingleMapper<ProductCoupon, Integer> {

	public void cancelByCouponId(@Param("shopId") Integer shopId, @Param("couponId") Integer couponId);

	public List<ProductCoupon> selectByCouponId(@Param("shopId") Integer shopId, @Param("couponId") Integer couponId);
}