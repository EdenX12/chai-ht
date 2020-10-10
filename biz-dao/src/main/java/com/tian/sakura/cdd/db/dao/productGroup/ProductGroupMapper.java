package com.tian.sakura.cdd.db.dao.productGroup;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.productGroup.ProductGroup;

public interface ProductGroupMapper extends AbstractSingleMapper<ProductGroup, Integer> {

	// 查询组内商品数量
	public List<HashMap> qryPrdGroupCntByShopId(Integer shopId);

	// 根据groupId,productId查询
	public ProductGroup qryByPrdIdGroupId(ProductGroup pg);

	// 删除组内商品
	public int deleteByGroupId(Integer groupId);

	// 删除组内指定商品
	public int deletePrdInGroup(@Param("shopId") Integer shopId, @Param("groupId") Integer groupId,
			@Param("productIds") String prdIds);
}