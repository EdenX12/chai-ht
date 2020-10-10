package com.tian.sakura.cdd.db.dao.shopUser;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;

public interface ShopUserMapper extends AbstractSingleMapper<ShopUser,Integer> {
 public ShopUser findByUserId(String userId);
}