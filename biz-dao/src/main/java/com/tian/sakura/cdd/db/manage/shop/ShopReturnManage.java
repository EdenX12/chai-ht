package com.tian.sakura.cdd.db.manage.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.shopReturn.ShopReturnMapper;
import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;
@Service
public class ShopReturnManage extends AbstractSingleManage<ShopReturn,Integer>{

	@Autowired
	private ShopReturnMapper shopReturnMapper;
	
	@Override
	protected AbstractSingleMapper<ShopReturn, Integer> getSingleMapper() {
		return shopReturnMapper;
	}

	//删除店铺退货/自提货地址
	public void deleteByShopId(Integer shopId,Integer addressType) {
		shopReturnMapper.deleteByShopId(shopId);
	}
	//查询店铺退货/自提货地址
	public List<ShopReturn> qryShopReturnByShopId(Integer shopId,Integer addressType){
		return shopReturnMapper.qryShopReturnByShopId(shopId,addressType);
	}
}
