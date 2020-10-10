package com.tian.sakura.cdd.db.domain.productType;

import com.tian.sakura.cdd.common.entity.BaseEntity;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_product_type
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ProductType extends BaseEntity<Integer> {
    /**
     * Database Column Remarks:
     * 商品分类表主键
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     * 分类名称
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.type_name
     *
     * @mbg.generated
     */
    private String typeName;

    /**
     * Database Column Remarks:
     * 分类图片
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.type_img
     *
     * @mbg.generated
     */
    private String typeImg;

    /**
     * Database Column Remarks:
     * 分类状态  0 不可用 1 可用
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.type_status
     *
     * @mbg.generated
     */
    private Integer typeStatus;

    private String sOrder;

    /**
     * Database Column Remarks:
     * 创建日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     * 创建人
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.create_user
     *
     * @mbg.generated
     */
    private Integer createUser;

    /**
     * Database Column Remarks:
     * 更新日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * Database Column Remarks:
     * 更新人
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_product_type.update_user
     *
     * @mbg.generated
     */
    private Integer updateUser;

    private String parentId;
    private int showOrder;
    private int level;
    private int flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getsOrder() {
        return sOrder;
    }

    public void setsOrder(String sOrder) {
        this.sOrder = sOrder;
        this.showOrder = Integer.valueOf(sOrder);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}