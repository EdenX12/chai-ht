package com.tian.sakura.cdd.db.domain.base;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_jpush_reg
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class JpushReg extends JpushRegKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_jpush_reg.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_jpush_reg.create_time
     *
     * @return the value of s_jpush_reg.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_jpush_reg.create_time
     *
     * @param createTime the value for s_jpush_reg.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}