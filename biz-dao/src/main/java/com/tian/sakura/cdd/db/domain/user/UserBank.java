package com.tian.sakura.cdd.db.domain.user;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import com.tian.sakura.cdd.db.domain.base.Bank;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_user_bank
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class UserBank  extends BaseEntity<String> {

    /**
     * Database Column Remarks:
     *   用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_bank.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * Database Column Remarks:
     *   银行id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_bank.bank_id
     *
     * @mbg.generated
     */
    private Integer bankId;

    private String bankCode;
    private String bankName;

    /**
     * Database Column Remarks:
     *   真实姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_bank.real_name
     *
     * @mbg.generated
     */
    private String realName;

    /**
     * Database Column Remarks:
     *   身份证号码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_bank.id_card
     *
     * @mbg.generated
     */
    private String idCard;

    /**
     * Database Column Remarks:
     *   银行卡号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_bank.card_num
     *
     * @mbg.generated
     */
    private String cardNum;

    private String mobile;

    /**
     * Database Column Remarks:
     *   0 可用 1 删除
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_bank.status
     *
     * @mbg.generated
     */
    private Integer  cardStatus;



    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_bank.user_id
     *
     * @return the value of s_user_bank.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_bank.user_id
     *
     * @param userId the value for s_user_bank.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_bank.bank_id
     *
     * @return the value of s_user_bank.bank_id
     *
     * @mbg.generated
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_bank.bank_id
     *
     * @param bankId the value for s_user_bank.bank_id
     *
     * @mbg.generated
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_bank.real_name
     *
     * @return the value of s_user_bank.real_name
     *
     * @mbg.generated
     */
    public String getRealName() {
        return realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_bank.real_name
     *
     * @param realName the value for s_user_bank.real_name
     *
     * @mbg.generated
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_bank.id_card
     *
     * @return the value of s_user_bank.id_card
     *
     * @mbg.generated
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_bank.id_card
     *
     * @param idCard the value for s_user_bank.id_card
     *
     * @mbg.generated
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_bank.card_num
     *
     * @return the value of s_user_bank.card_num
     *
     * @mbg.generated
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_bank.card_num
     *
     * @param cardNum the value for s_user_bank.card_num
     *
     * @mbg.generated
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}