package com.tian.sakura.cdd.db.domain.product;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_product_spec
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class ProductSpec extends BaseEntity<String> {
    /**
     * Database Column Remarks:
     * 商品规格表主键
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     * 商品ID
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.product_id
     *
     * @mbg.generated
     */
    private String productId;

    /**
     * Database Column Remarks:
     * 规格值（用下划线连接）
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.product_spec_value_id
     *
     * @mbg.generated
     */
    private String productSpecValueId;

    /**
     * Database Column Remarks:
     * 规格值名称（颜色 用下划线连接）
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.product_spec_value_type
     *
     * @mbg.generated
     */
    private String productSpecValueType;

    /**
     * Database Column Remarks:
     * 规格值名称（黑色 用下划线连接）
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.product_spec_value_name
     *
     * @mbg.generated
     */
    private String productSpecValueName;

    /**
     * Database Column Remarks:
     * 商品数量
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.product_number
     *
     * @mbg.generated
     */
    private Integer productNumber;

    private Integer stockNumber;

    /**
     * Database Column Remarks:
     * 商品价格（单位元）
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.product_price
     *
     * @mbg.generated
     */
    private BigDecimal productPrice;

    /**
     * Database Column Remarks:
     * 划线价格
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.scribing_price
     *
     * @mbg.generated
     */
    private BigDecimal scribingPrice;

    /**
     * Database Column Remarks:
     * 创建日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     * 更新日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * Database Column Remarks:
     * 创建人
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.create_user
     *
     * @mbg.generated
     */
    private Integer createUser;

    /**
     * Database Column Remarks:
     * 更新人
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_spec.update_user
     *
     * @mbg.generated
     */
    private Integer updateUser;

    private BigDecimal costPrice;

}