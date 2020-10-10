package com.tian.sakura.cdd.db.domain.activity;

import java.util.Date;

import com.tian.sakura.cdd.common.entity.BaseEntity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_activity
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Activity extends BaseEntity<String> {
    /**
     * Database Column Remarks:
     *   活动主键ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     *   活动名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.act_name
     *
     * @mbg.generated
     */
    private String actName;

    /**
     * Database Column Remarks:
     *   活动类型
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.act_type
     *
     * @mbg.generated
     */
    private String actType;

    /**
     * Database Column Remarks:
     *   广告图片
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.picture_url
     *
     * @mbg.generated
     */
    private String pictureUrl;

    /**
     * Database Column Remarks:
     *   状态 0-创建 1-发布 2-下架 3-删除
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.act_status
     *
     * @mbg.generated
     */
    private Integer actStatus;

    /**
     * Database Column Remarks:
     *   显示顺序
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.s_order
     *
     * @mbg.generated
     */
    private Integer sOrder;

    /**
     * Database Column Remarks:
     *   倒计时标记0-不需要倒计时 1-需要
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.flag
     *
     * @mbg.generated
     */
    private Integer flag;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * Database Column Remarks:
     *   创建者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.create_user
     *
     * @mbg.generated
     */
    private Integer createUser;

    /**
     * Database Column Remarks:
     *   更新者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activity.update_user
     *
     * @mbg.generated
     */
    private Integer updateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.id
     *
     * @return the value of s_activity.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.id
     *
     * @param id the value for s_activity.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.act_name
     *
     * @return the value of s_activity.act_name
     *
     * @mbg.generated
     */
    public String getActName() {
        return actName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.act_name
     *
     * @param actName the value for s_activity.act_name
     *
     * @mbg.generated
     */
    public void setActName(String actName) {
        this.actName = actName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.act_type
     *
     * @return the value of s_activity.act_type
     *
     * @mbg.generated
     */
    public String getActType() {
        return actType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.act_type
     *
     * @param actType the value for s_activity.act_type
     *
     * @mbg.generated
     */
    public void setActType(String actType) {
        this.actType = actType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.picture_url
     *
     * @return the value of s_activity.picture_url
     *
     * @mbg.generated
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.picture_url
     *
     * @param pictureUrl the value for s_activity.picture_url
     *
     * @mbg.generated
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.act_status
     *
     * @return the value of s_activity.act_status
     *
     * @mbg.generated
     */
    public Integer getActStatus() {
        return actStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.act_status
     *
     * @param actStatus the value for s_activity.act_status
     *
     * @mbg.generated
     */
    public void setActStatus(Integer actStatus) {
        this.actStatus = actStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.s_order
     *
     * @return the value of s_activity.s_order
     *
     * @mbg.generated
     */
    public Integer getsOrder() {
        return sOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.s_order
     *
     * @param sOrder the value for s_activity.s_order
     *
     * @mbg.generated
     */
    public void setsOrder(Integer sOrder) {
        this.sOrder = sOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.flag
     *
     * @return the value of s_activity.flag
     *
     * @mbg.generated
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.flag
     *
     * @param flag the value for s_activity.flag
     *
     * @mbg.generated
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.create_time
     *
     * @return the value of s_activity.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.create_time
     *
     * @param createTime the value for s_activity.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.update_time
     *
     * @return the value of s_activity.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.update_time
     *
     * @param updateTime the value for s_activity.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.create_user
     *
     * @return the value of s_activity.create_user
     *
     * @mbg.generated
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.create_user
     *
     * @param createUser the value for s_activity.create_user
     *
     * @mbg.generated
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activity.update_user
     *
     * @return the value of s_activity.update_user
     *
     * @mbg.generated
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activity.update_user
     *
     * @param updateUser the value for s_activity.update_user
     *
     * @mbg.generated
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}