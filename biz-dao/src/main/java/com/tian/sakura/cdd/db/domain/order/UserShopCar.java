package com.tian.sakura.cdd.db.domain.order;

import com.tian.sakura.cdd.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_user_shop_car
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class UserShopCar extends BaseEntity<String> {

    /**
     * Database Column Remarks:
     * 用户标识
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * Database Column Remarks:
     * 产品标识
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.product_id
     *
     * @mbg.generated
     */
    private String productId;
    private String productName;
    private String productImg;
    private String shopId;
    private String shopName;
    private Integer taskNumber;
    private BigDecimal totalReward;
    private BigDecimal taskPrice;

    /**
     * Database Column Remarks:
     * 商品规格主键
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.product_spec_id
     *
     * @mbg.generated
     */
    private String productSpecId;
    private String productSpecValueName;

    /**
     * Database Column Remarks:
     * 最初价格
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * Database Column Remarks:
     * 数量
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.count
     *
     * @mbg.generated
     */
    private Integer count;

    /**
     * Database Column Remarks:
     * 选中状态 0-否 ；1-是
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.check_status
     *
     * @mbg.generated
     */
    private Integer checkStatus;

    /**
     * Database Column Remarks:
     * 唯一标识
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_shop_car.extend_ref
     *
     * @mbg.generated
     */
    private String extendRef;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.user_id
     *
     * @return the value of s_user_shop_car.user_id
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.user_id
     *
     * @param userId the value for s_user_shop_car.user_id
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.product_id
     *
     * @return the value of s_user_shop_car.product_id
     * @mbg.generated
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.product_id
     *
     * @param productId the value for s_user_shop_car.product_id
     * @mbg.generated
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.product_spec_id
     *
     * @return the value of s_user_shop_car.product_spec_id
     * @mbg.generated
     */
    public String getProductSpecId() {
        return productSpecId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.product_spec_id
     *
     * @param productSpecId the value for s_user_shop_car.product_spec_id
     * @mbg.generated
     */
    public void setProductSpecId(String productSpecId) {
        this.productSpecId = productSpecId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.price
     *
     * @return the value of s_user_shop_car.price
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.price
     *
     * @param price the value for s_user_shop_car.price
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.count
     *
     * @return the value of s_user_shop_car.count
     * @mbg.generated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.count
     *
     * @param count the value for s_user_shop_car.count
     * @mbg.generated
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.check_status
     *
     * @return the value of s_user_shop_car.check_status
     * @mbg.generated
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.check_status
     *
     * @param checkStatus the value for s_user_shop_car.check_status
     * @mbg.generated
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_shop_car.extend_ref
     *
     * @return the value of s_user_shop_car.extend_ref
     * @mbg.generated
     */
    public String getExtendRef() {
        return extendRef;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_shop_car.extend_ref
     *
     * @param extendRef the value for s_user_shop_car.extend_ref
     * @mbg.generated
     */
    public void setExtendRef(String extendRef) {
        this.extendRef = extendRef;
    }

    public String getProductSpecValueName() {
        return productSpecValueName;
    }

    public void setProductSpecValueName(String productSpecValueName) {
        this.productSpecValueName = productSpecValueName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public BigDecimal getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(BigDecimal totalReward) {
        this.totalReward = totalReward;
    }

    public BigDecimal getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(BigDecimal taskPrice) {
        this.taskPrice = taskPrice;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}