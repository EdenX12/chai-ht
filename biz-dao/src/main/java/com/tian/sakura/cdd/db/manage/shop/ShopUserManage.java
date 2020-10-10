package com.tian.sakura.cdd.db.manage.shop;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.shopUser.ShopUserMapper;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopUserManage extends AbstractSingleManage<ShopUser,Integer>{
	@Autowired
	private ShopUserMapper shopUserMapper;
	
	@Override
	protected AbstractSingleMapper<ShopUser, Integer> getSingleMapper() {
		return shopUserMapper;
	}

	//根据用户id查询
	public ShopUser findByUserId(String userId) {
		return 	shopUserMapper.findByUserId(userId);
	}
}
