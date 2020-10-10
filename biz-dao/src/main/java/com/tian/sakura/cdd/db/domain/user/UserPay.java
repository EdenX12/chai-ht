package com.tian.sakura.cdd.db.domain.user;

import com.tian.sakura.cdd.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_user_pay
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class UserPay extends BaseEntity<String> {

    private String paySn;

    private String userId;

    private Integer payType;

    private String relationId;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private String userCouponId;

    private Integer payStatus;

    private Date payTime;

    private String transSn;



    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.pay_sn
     *
     * @return the value of s_user_pay.pay_sn
     * @mbg.generated
     */
    public String getPaySn() {
        return paySn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.pay_sn
     *
     * @param paySn the value for s_user_pay.pay_sn
     * @mbg.generated
     */
    public void setPaySn(String paySn) {
        this.paySn = paySn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.user_id
     *
     * @return the value of s_user_pay.user_id
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.user_id
     *
     * @param userId the value for s_user_pay.user_id
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.pay_type
     *
     * @return the value of s_user_pay.pay_type
     * @mbg.generated
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.pay_type
     *
     * @param payType the value for s_user_pay.pay_type
     * @mbg.generated
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.relation_id
     *
     * @return the value of s_user_pay.relation_id
     * @mbg.generated
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.relation_id
     *
     * @param relationId the value for s_user_pay.relation_id
     * @mbg.generated
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.total_amount
     *
     * @return the value of s_user_pay.total_amount
     * @mbg.generated
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.total_amount
     *
     * @param totalAmount the value for s_user_pay.total_amount
     * @mbg.generated
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.pay_amount
     *
     * @return the value of s_user_pay.pay_amount
     * @mbg.generated
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.pay_amount
     *
     * @param payAmount the value for s_user_pay.pay_amount
     * @mbg.generated
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.user_coupon_id
     *
     * @return the value of s_user_pay.user_coupon_id
     * @mbg.generated
     */
    public String getUserCouponId() {
        return userCouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.user_coupon_id
     *
     * @param userCouponId the value for s_user_pay.user_coupon_id
     * @mbg.generated
     */
    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.pay_status
     *
     * @return the value of s_user_pay.pay_status
     * @mbg.generated
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.pay_status
     *
     * @param payStatus the value for s_user_pay.pay_status
     * @mbg.generated
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.pay_time
     *
     * @return the value of s_user_pay.pay_time
     * @mbg.generated
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.pay_time
     *
     * @param payTime the value for s_user_pay.pay_time
     * @mbg.generated
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_pay.trans_sn
     *
     * @return the value of s_user_pay.trans_sn
     * @mbg.generated
     */
    public String getTransSn() {
        return transSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_pay.trans_sn
     *
     * @param transSn the value for s_user_pay.trans_sn
     * @mbg.generated
     */
    public void setTransSn(String transSn) {
        this.transSn = transSn;
    }

}