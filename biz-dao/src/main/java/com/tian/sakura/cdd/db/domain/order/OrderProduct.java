package com.tian.sakura.cdd.db.domain.order;

import com.tian.sakura.cdd.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_order_product
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class OrderProduct extends BaseEntity<String> {
    /**
     * Database Column Remarks:
     *   订单商品索引id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     *   用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * Database Column Remarks:
     *   订单ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.order_detail_id
     *
     * @mbg.generated
     */
    private String orderDetailId;

    /**
     * Database Column Remarks:
     *   商品id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_id
     *
     * @mbg.generated
     */
    private String productId;

    private String productSpecId;

    /**
     * Database Column Remarks:
     *   商品规格名称（下划线连接多个）
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_spec_value_name
     *
     * @mbg.generated
     */
    private String productSpecValueName;

    /**
     * Database Column Remarks:
     *   购买数量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_number
     *
     * @mbg.generated
     */
    private Integer productNumber;

    /**
     * Database Column Remarks:
     *   商品价格
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_price
     *
     * @mbg.generated
     */
    private BigDecimal productPrice;

    /**
     * Database Column Remarks:
     *   划线价格
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.scribing_price
     *
     * @mbg.generated
     */
    private BigDecimal scribingPrice;

    /**
     * Database Column Remarks:
     *   总佣金
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.total_reward
     *
     * @mbg.generated
     */
    private BigDecimal totalReward;

    /**
     * Database Column Remarks:
     *   任务金
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.task_price
     *
     * @mbg.generated
     */
    private BigDecimal taskPrice;

    /**
     * Database Column Remarks:
     *   商品名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_name
     *
     * @mbg.generated
     */
    private String productName;

    /**
     * Database Column Remarks:
     *   商品图片
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_img
     *
     * @mbg.generated
     */
    private String productImg;

    /**
     * Database Column Remarks:
     *   商品简介
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_des
     *
     * @mbg.generated
     */
    private String productDes;

    /**
     * Database Column Remarks:
     *   订单生成时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   商品详情
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_order_product.product_detail
     *
     * @mbg.generated
     */
    private String productDetail;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.id
     *
     * @return the value of s_order_product.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.id
     *
     * @param id the value for s_order_product.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.user_id
     *
     * @return the value of s_order_product.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.user_id
     *
     * @param userId the value for s_order_product.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.order_detail_id
     *
     * @return the value of s_order_product.order_detail_id
     *
     * @mbg.generated
     */
    public String getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.order_detail_id
     *
     * @param orderDetailId the value for s_order_product.order_detail_id
     *
     * @mbg.generated
     */
    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_id
     *
     * @return the value of s_order_product.product_id
     *
     * @mbg.generated
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_id
     *
     * @param productId the value for s_order_product.product_id
     *
     * @mbg.generated
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_spec_value_name
     *
     * @return the value of s_order_product.product_spec_value_name
     *
     * @mbg.generated
     */
    public String getProductSpecValueName() {
        return productSpecValueName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_spec_value_name
     *
     * @param productSpecValueName the value for s_order_product.product_spec_value_name
     *
     * @mbg.generated
     */
    public void setProductSpecValueName(String productSpecValueName) {
        this.productSpecValueName = productSpecValueName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_number
     *
     * @return the value of s_order_product.product_number
     *
     * @mbg.generated
     */
    public Integer getProductNumber() {
        return productNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_number
     *
     * @param productNumber the value for s_order_product.product_number
     *
     * @mbg.generated
     */
    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_price
     *
     * @return the value of s_order_product.product_price
     *
     * @mbg.generated
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_price
     *
     * @param productPrice the value for s_order_product.product_price
     *
     * @mbg.generated
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.scribing_price
     *
     * @return the value of s_order_product.scribing_price
     *
     * @mbg.generated
     */
    public BigDecimal getScribingPrice() {
        return scribingPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.scribing_price
     *
     * @param scribingPrice the value for s_order_product.scribing_price
     *
     * @mbg.generated
     */
    public void setScribingPrice(BigDecimal scribingPrice) {
        this.scribingPrice = scribingPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.total_reward
     *
     * @return the value of s_order_product.total_reward
     *
     * @mbg.generated
     */
    public BigDecimal getTotalReward() {
        return totalReward;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.total_reward
     *
     * @param totalReward the value for s_order_product.total_reward
     *
     * @mbg.generated
     */
    public void setTotalReward(BigDecimal totalReward) {
        this.totalReward = totalReward;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.task_price
     *
     * @return the value of s_order_product.task_price
     *
     * @mbg.generated
     */
    public BigDecimal getTaskPrice() {
        return taskPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.task_price
     *
     * @param taskPrice the value for s_order_product.task_price
     *
     * @mbg.generated
     */
    public void setTaskPrice(BigDecimal taskPrice) {
        this.taskPrice = taskPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_name
     *
     * @return the value of s_order_product.product_name
     *
     * @mbg.generated
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_name
     *
     * @param productName the value for s_order_product.product_name
     *
     * @mbg.generated
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_img
     *
     * @return the value of s_order_product.product_img
     *
     * @mbg.generated
     */
    public String getProductImg() {
        return productImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_img
     *
     * @param productImg the value for s_order_product.product_img
     *
     * @mbg.generated
     */
    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_des
     *
     * @return the value of s_order_product.product_des
     *
     * @mbg.generated
     */
    public String getProductDes() {
        return productDes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_des
     *
     * @param productDes the value for s_order_product.product_des
     *
     * @mbg.generated
     */
    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.create_time
     *
     * @return the value of s_order_product.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.create_time
     *
     * @param createTime the value for s_order_product.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_order_product.product_detail
     *
     * @return the value of s_order_product.product_detail
     *
     * @mbg.generated
     */
    public String getProductDetail() {
        return productDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_order_product.product_detail
     *
     * @param productDetail the value for s_order_product.product_detail
     *
     * @mbg.generated
     */
    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(String productSpecId) {
        this.productSpecId = productSpecId;
    }
    
    private Integer deliveryType;//提货方式

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
    
    
    
}