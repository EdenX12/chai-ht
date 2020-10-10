package com.tian.sakura.cdd.db.dao.shopReturn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;

public interface ShopReturnMapper extends AbstractSingleMapper<ShopReturn,Integer>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_return
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_return
     *
     * @mbg.generated
     */
    int insert(ShopReturn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_return
     *
     * @mbg.generated
     */
    int insertSelective(ShopReturn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_return
     *
     * @mbg.generated
     */
    ShopReturn selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_return
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShopReturn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_shop_return
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShopReturn record);
    
    //根据店铺id删除退货地址
    int deleteByShopId(Integer shopId);
    //查询店铺退货地址
	List<ShopReturn> qryShopReturnByShopId(@Param("shopId")Integer shopId,@Param("addressType")Integer addressType);
}