package com.tian.sakura.cdd.db.domain.sms;

import com.tian.sakura.cdd.common.entity.BaseEntity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_sms_code
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class SmsCode extends BaseEntity<String> {
//    /**
//     * Database Column Remarks:
//     *   索引ID
//     *
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database column s_sms_code.id
//     *
//     * @mbg.generated
//     */
//    private Integer id;

    /**
     * Database Column Remarks:
     *   手机号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_sms_code.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     * Database Column Remarks:
     *   验证码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_sms_code.sms_code
     *
     * @mbg.generated
     */
    private String smsCode;

    private String useStatus;
    private Integer expiryDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_sms_code.mobile
     *
     * @return the value of s_sms_code.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_sms_code.mobile
     *
     * @param mobile the value for s_sms_code.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_sms_code.sms_code
     *
     * @return the value of s_sms_code.sms_code
     *
     * @mbg.generated
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_sms_code.sms_code
     *
     * @param smsCode the value for s_sms_code.sms_code
     *
     * @mbg.generated
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Integer expiryDate) {
        this.expiryDate = expiryDate;
    }
}