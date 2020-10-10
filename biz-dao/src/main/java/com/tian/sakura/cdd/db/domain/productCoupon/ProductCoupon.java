package com.tian.sakura.cdd.db.domain.productCoupon;

import java.util.Date;

import com.tian.sakura.cdd.common.entity.BaseEntity;

import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_product_coupon
 *
 * @mbg.generated do_not_delete_during_merge
 */

public class ProductCoupon  extends BaseEntity<Integer>{
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_coupon.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   店铺id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_coupon.shop_id
     *
     * @mbg.generated
     */
    private Integer shopId;

    /**
     * Database Column Remarks:
     *   组id或者商品id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_coupon.group_or_product_id
     *
     * @mbg.generated
     */
    private String groupOrProductId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_coupon.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_coupon.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_product_coupon.id
     *
     * @return the value of s_product_coupon.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_product_coupon.id
     *
     * @param id the value for s_product_coupon.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_product_coupon.shop_id
     *
     * @return the value of s_product_coupon.shop_id
     *
     * @mbg.generated
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_product_coupon.shop_id
     *
     * @param shopId the value for s_product_coupon.shop_id
     *
     * @mbg.generated
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_product_coupon.group_or_product_id
     *
     * @return the value of s_product_coupon.group_or_product_id
     *
     * @mbg.generated
     */
    public String getGroupOrProductId() {
        return groupOrProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_product_coupon.group_or_product_id
     *
     * @param groupOrProductId the value for s_product_coupon.group_or_product_id
     *
     * @mbg.generated
     */
    public void setGroupOrProductId(String groupOrProductId) {
        this.groupOrProductId = groupOrProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_product_coupon.create_time
     *
     * @return the value of s_product_coupon.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_product_coupon.create_time
     *
     * @param createTime the value for s_product_coupon.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_product_coupon.update_time
     *
     * @return the value of s_product_coupon.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_product_coupon.update_time
     *
     * @param updateTime the value for s_product_coupon.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     * 券id
     */
    private Integer couponId;

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
    
    
}