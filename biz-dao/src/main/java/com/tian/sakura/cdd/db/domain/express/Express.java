package com.tian.sakura.cdd.db.domain.express;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import lombok.Data;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_express
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class Express extends BaseEntity<String> {
    /**
     * Database Column Remarks:
     * 快递主键ID
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     * 公司名称
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.e_name
     *
     * @mbg.generated
     */
    private String eName;

    /**
     * Database Column Remarks:
     * 状态
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.e_state
     *
     * @mbg.generated
     */
    private Integer eState;

    /**
     * Database Column Remarks:
     * 编号
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.e_code
     *
     * @mbg.generated
     */
    private String eCode;

    /**
     * Database Column Remarks:
     * 首字母
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.e_letter
     *
     * @mbg.generated
     */
    private String eLetter;

    /**
     * Database Column Remarks:
     * 1常用0不常用
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.e_order
     *
     * @mbg.generated
     */
    private Integer eOrder;

    /**
     * Database Column Remarks:
     * 公司网址
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.e_url
     *
     * @mbg.generated
     */
    private String eUrl;

    /**
     * Database Column Remarks:
     * 删除标记
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_express.is_del
     *
     * @mbg.generated
     */
    private Integer isDel;
}