package com.tian.sakura.cdd.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 实体基类
 *
 * @author lvzonggang
 */

@Getter
@Setter
@ToString
public class BaseEntity<ID> {

    /** 主键 */
    private ID id;
    /** 数据状态 */
    private String status;
    /** 创建时间 */
    private Date createTime;
    private Date addTime;
    /** 更新时间 */
    private Date updateTime;
    /** 创建人 */
    private String createOper;
    /** 更新人 */
    private String updateOper;

    private int dataVersion;


}
