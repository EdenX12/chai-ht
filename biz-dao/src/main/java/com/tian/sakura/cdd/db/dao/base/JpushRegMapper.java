package com.tian.sakura.cdd.db.dao.base;

import com.tian.sakura.cdd.db.domain.base.JpushReg;
import com.tian.sakura.cdd.db.domain.base.JpushRegKey;

import java.util.List;

public interface JpushRegMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_jpush_reg
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(JpushRegKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_jpush_reg
     *
     * @mbg.generated
     */
    int insert(JpushReg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_jpush_reg
     *
     * @mbg.generated
     */
    int insertSelective(JpushReg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_jpush_reg
     *
     * @mbg.generated
     */
    JpushReg selectByPrimaryKey(JpushRegKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_jpush_reg
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(JpushReg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_jpush_reg
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(JpushReg record);

    List<JpushReg> selectByDeviceId(String deviceId);
}