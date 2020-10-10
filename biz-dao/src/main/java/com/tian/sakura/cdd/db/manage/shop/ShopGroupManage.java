package com.tian.sakura.cdd.db.manage.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.shopGroup.ShopGroupMapper;
import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;

@Service
public class ShopGroupManage extends AbstractSingleManage<ShopGroup,Integer>{

	@Autowired
	private ShopGroupMapper shopGroupMapper;
	
	@Override
	protected AbstractSingleMapper<ShopGroup, Integer> getSingleMapper() {
		return shopGroupMapper;
	}
   //查询分组
	public List<ShopGroup> qryShopGroupByShopId(Integer shopId){
		return shopGroupMapper.qryShopGroupByShopId(shopId);
	}
	public List<ShopGroup> qryByPrdId(Integer shopId,String productId){
		return shopGroupMapper.qryByPrdId(shopId, productId);
	}
}
