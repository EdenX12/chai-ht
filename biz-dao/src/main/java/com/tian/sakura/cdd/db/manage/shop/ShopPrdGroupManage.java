package com.tian.sakura.cdd.db.manage.shop;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.productGroup.ProductGroupMapper;
import com.tian.sakura.cdd.db.domain.productGroup.ProductGroup;

@Service
public class ShopPrdGroupManage extends AbstractSingleManage<ProductGroup,Integer>{
	@Autowired
	private ProductGroupMapper prdGroupMapper;
	
	@Override
	protected AbstractSingleMapper<ProductGroup, Integer> getSingleMapper() {
		return prdGroupMapper;
	}

	//查询组内商品数量
	public List<HashMap> qryPrdGroupCntByShopId(Integer shopId){
		return prdGroupMapper.qryPrdGroupCntByShopId(shopId);
	}
	
	//查询组内商品
	public ProductGroup qryByPrdIdGroupId(ProductGroup pg){
		return prdGroupMapper.qryByPrdIdGroupId(pg);
	}
	
    //删除组内所以商品
    public int deleteByGroupId(Integer groupId) {
    	return prdGroupMapper.deleteByGroupId(groupId);
    }

    //删除组内指定商品
    public int deletePrdInGroup(Integer shopId,Integer groupId,String prdIds) {
    	return prdGroupMapper.deletePrdInGroup(shopId,groupId,prdIds);
    }

}
