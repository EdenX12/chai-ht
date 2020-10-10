package com.tian.sakura.cdd.db.dao.shopGroup;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopGroupMapper extends AbstractSingleMapper<ShopGroup,Integer>{

	//根据店铺id查询商品分组
	List<ShopGroup> qryShopGroupByShopId(Integer shopId);
	//
	public List<ShopGroup> qryByPrdId(@Param("shopId") Integer shopId,@Param("productId")String productId);
}