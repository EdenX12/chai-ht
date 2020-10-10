package com.tian.sakura.cdd.common.entity;

import java.util.List;

/**
 * 单表mapper映射
 *
 * @author lvzonggang
 */
public interface AbstractSingleMapper<T, ID> {
    /**
     * 物理删除
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(ID id);

    /**
     * 插入
     * @param record 记录
     * @return
     */
    int insert(T record);

    /**
     * 选择性插入值
     * @param record 记录
     * @return
     */
    int insertSelective(T record);

    /**
     * 主键查询
     * @param id 主键
     * @return
     */
    T selectByPrimaryKey(ID id);

    /**
     * 更新
     * @param record 记录
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 更新
     * @param record 记录
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<T> list);
}
