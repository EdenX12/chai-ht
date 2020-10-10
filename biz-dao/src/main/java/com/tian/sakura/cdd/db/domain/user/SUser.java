package com.tian.sakura.cdd.db.domain.user;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_user
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class SUser extends BaseEntity<String> {

    /**
     * Database Column Remarks:
     * 用户名（先写手机号）
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Database Column Remarks:
     * 用户昵称
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.nick_name
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * Database Column Remarks:
     * 密码
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.user_password
     *
     * @mbg.generated
     */
    private String userPassword;

    /**
     * Database Column Remarks:
     * 创建日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     * 用户头像
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.user_img
     *
     * @mbg.generated
     */
    private String userImg;

    /**
     * Database Column Remarks:
     * 微信openId(打款时需要)
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.open_id
     *
     * @mbg.generated
     */
    private String openId;

    /**
     * Database Column Remarks:
     * 用户手机号码
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.user_phone
     *
     * @mbg.generated
     */
    private String userPhone;

    /**
     * Database Column Remarks:
     * 余额
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.total_amount
     *
     * @mbg.generated
     */
    private BigDecimal totalAmount;

    /**
     * Database Column Remarks:
     * 锁定金额
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lock_amount
     *
     * @mbg.generated
     */
    private BigDecimal lockAmount;

    /**
     * Database Column Remarks:
     * 领取任务次数
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.task_count
     *
     * @mbg.generated
     */
    private Integer taskCount;

    /**
     * Database Column Remarks:
     * 猎豆数量
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.reward_bean
     *
     * @mbg.generated
     */
    private Integer rewardBean;

    /**
     * Database Column Remarks:
     * 0 可用  1 不可用
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.user_status
     *
     * @mbg.generated
     */
    private Integer userStatus;

    /**
     * Database Column Remarks:
     * 可以使用的猎豆数量
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.canuse_bean
     *
     * @mbg.generated
     */
    private Integer canuseBean;

    /**
     * Database Column Remarks:
     * 上级id
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.parent_id
     *
     * @mbg.generated
     */
    private String parentId;

    /**
     * Database Column Remarks:
     * 1、普通客户；2-商家；；3-拆家；4-即是拆家也是商家
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.user_type
     *
     * @mbg.generated
     */
    private Integer userType;

    /**
     * Database Column Remarks:
     * 每个客户唯一，不依赖于数据库的sequence
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.external_ref
     *
     * @mbg.generated
     */
    private String externalRef;
    private String unionId;
    private String appOpenId;
    private Date lastLogin;
    private String inviteCode;
    private Integer channel;
    private Integer userLevelType;
    private String userLevelTypeName;

    private BigDecimal taskAmount;
    private Integer taskNum;
    private Integer taskNumSum;
    private BigDecimal orderAmount;
    private Integer orderNum;
}