package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopCatlog.ShopCatlog;
import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;
import com.tian.sakura.cdd.db.manage.shop.ShopCatlogManage;
import com.tian.sakura.cdd.db.manage.shop.ShopGroupManage;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopManage shopManage;
    @Autowired
    private ShopCatlogManage shopCatlogManage;
    @Autowired
    private ShopGroupManage shopGroupManage;

    public PageInfo<Shop> listShop(ShopReq shopReq) {
        PageHelper.startPage(shopReq.getPageNum(), shopReq.getPageSize());
        return new PageInfo<>(shopManage.listShop(shopReq));
    }

    public void insertShop(Shop shop) {
        shopManage.insertSelective(shop);
    }

    public void updateShop(Shop shop) {
        shopManage.updateByPrimaryKeySelective(shop);
    }

    public void deleteShop(Shop shop) {
        shopManage.deleteByPrimaryKey(shop.getId());
    }

    public List<ShopCatlog> listCatRel(ShopCatlog shopCatlog) {
        return shopCatlogManage.qryShopCatlog(shopCatlog.getShopId());
    }

    public List<ShopGroup> listGroup(ShopCatlog shopCatlog) {
        return shopGroupManage.qryShopGroupByShopId(shopCatlog.getShopId());
    }
}
