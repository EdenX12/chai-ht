package com.tian.sakura.cdd.db.manage.shop;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.dao.shop.ShopMapper;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopManage extends AbstractSingleManage<Shop, Integer> {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    protected AbstractSingleMapper<Shop, Integer> getSingleMapper() {
        return shopMapper;
    }

    public List<Shop> listShop(ShopReq shopReq) {
        return shopMapper.listShop(shopReq);
    }
    
    //商铺基本信息
    public int basicInsert(Shop shop) {
    	int shopId = shopMapper.insert(shop);
    	return shopId;
    }
    
    
}
