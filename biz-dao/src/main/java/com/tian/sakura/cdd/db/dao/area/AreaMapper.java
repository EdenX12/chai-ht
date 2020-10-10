package com.tian.sakura.cdd.db.dao.area;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.area.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper extends AbstractSingleMapper<Area, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_area
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_area
     *
     * @mbg.generated
     */
    int insert(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_area
     *
     * @mbg.generated
     */
    int insertSelective(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_area
     *
     * @mbg.generated
     */
    Area selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_area
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_area
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Area record);

    List<Area> getParentList();

    List<Area> getChildListById(@Param("id") Integer id);

    List<Area> findAll();
}