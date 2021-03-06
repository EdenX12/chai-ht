package com.tian.sakura.cdd.db.domain.shop;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_shop_coupon
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class ShopCoupon extends BaseEntity<String> {
    /**
     * Database Column Remarks:
     * 商铺优惠券主键ID
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     * 商铺id
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.shop_id
     *
     * @mbg.generated
     */
    private String shopId;

    /**
     * Database Column Remarks:
     * 商品ID
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.product_id
     *
     * @mbg.generated
     */
    private String productId;

    /**
     * Database Column Remarks:
     * 券名称
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.coupon_name
     *
     * @mbg.generated
     */
    private String couponName;

    /**
     * Database Column Remarks:
     * 券面值
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.coupon_amount
     *
     * @mbg.generated
     */
    private BigDecimal couponAmount;

    /**
     * Database Column Remarks:
     * 券开始日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.start_date
     *
     * @mbg.generated
     */
    private Date startDate;

    /**
     * Database Column Remarks:
     * 券截止日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.end_date
     *
     * @mbg.generated
     */
    private Date endDate;

    /**
     * Database Column Remarks:
     * 券数量
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.coupon_quantity
     *
     * @mbg.generated
     */
    private Integer couponQuantity;

    /**
     * Database Column Remarks:
     * 每个人可以领取的最大数量
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.per_limit
     *
     * @mbg.generated
     */
    private Integer perLimit;

    /**
     * Database Column Remarks:
     * 最低消费金额
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.min_consume_amount
     *
     * @mbg.generated
     */
    private BigDecimal minConsumeAmount;

    /**
     * Database Column Remarks:
     * 使用条件 0-立减 1-满减
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.use_con
     *
     * @mbg.generated
     */
    private Integer useCon;

    /**
     * Database Column Remarks:
     * 状态 0-创建 1-发布 2-下架 3-删除
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.coupon_status
     *
     * @mbg.generated
     */
    private Integer couponStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.create_user
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_shop_coupon.update_user
     *
     * @mbg.generated
     */
    private String updateUser;

    /**
     * 商品名称
     */
    private String productName;
    
    /**
     * 有效期天数
     */
    private Integer limitDays;
    /**
     * 适用范围
     */
    private Integer productScope;
}