package com.tian.sakura.cdd.db.manage.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.shopCatlog.ShopCatlogMapper;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopCatlog.ShopCatlog;

@Service
public class ShopCatlogManage extends AbstractSingleManage<ShopCatlog, Integer> {

	@Autowired
	private ShopCatlogMapper shopCatlogMapper;
	
	@Override
	protected AbstractSingleMapper<ShopCatlog, Integer> getSingleMapper() {
		return shopCatlogMapper;
	}
	
	//查询店铺经营类目
	public  List<ShopCatlog> qryShopCatlog (Integer shopId){
		return shopCatlogMapper.qryShopCatlog(shopId);
	};
}
