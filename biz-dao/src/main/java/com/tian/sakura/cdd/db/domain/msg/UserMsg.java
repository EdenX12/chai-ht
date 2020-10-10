package com.tian.sakura.cdd.db.domain.msg;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_user_msg
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class UserMsg extends BaseEntity<String> {
    /**
     * Database Column Remarks:
     * 我的消息主键
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     * 用户id
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * Database Column Remarks:
     * 消息日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.msg_time
     *
     * @mbg.generated
     */
    private Date msgTime;

    /**
     * Database Column Remarks:
     * 0 未读 1 已读
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.status
     *
     * @mbg.generated
     */
    private Integer readStatus;

    /**
     * Database Column Remarks:
     * 0 首页消息 1 独赢赏金到账 2 躺赢赏金到账 3 下级奖励到账
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.msg_type
     *
     * @mbg.generated
     */
    private Integer msgType;

    /**
     * Database Column Remarks:
     * 消息标题
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.msg_title
     *
     * @mbg.generated
     */
    private String msgTitle;

    /**
     * Database Column Remarks:
     * 消息内容
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_msg.msg_info
     *
     * @mbg.generated
     */
    private String msgInfo;

}