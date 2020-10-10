package com.tian.sakura.cdd.db.dao.shop;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.shop.ShopCoupon;
import com.tian.sakura.cdd.db.manage.shop.vo.ShopCouponQryVo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCouponMapper extends AbstractSingleMapper<ShopCoupon, String> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_coupon
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_coupon
     *
     * @mbg.generated
     */
    int insert(ShopCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_coupon
     *
     * @mbg.generated
     */
    int insertSelective(ShopCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_coupon
     *
     * @mbg.generated
     */
    ShopCoupon selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShopCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShopCoupon record);

    List<ShopCoupon> selectShopCouponByShopId(@Param("shopId") String shopId);
    
    List<ShopCoupon> getCouponPage(ShopCouponQryVo vo);
}