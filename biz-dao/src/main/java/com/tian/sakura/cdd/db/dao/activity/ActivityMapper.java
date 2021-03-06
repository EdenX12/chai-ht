package com.tian.sakura.cdd.db.dao.activity;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.activity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper extends AbstractSingleMapper<Activity, String> {

    List<Activity> getAllActivity();


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activity
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activity
     *
     * @mbg.generated
     */
    int insert(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activity
     *
     * @mbg.generated
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activity
     *
     * @mbg.generated
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activity
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activity
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Activity record);

    List<Activity> listActivity(Activity activity);

    int deleteActivity(@Param("id") String id);
}