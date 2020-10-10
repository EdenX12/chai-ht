package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.domain.shop.ShopCoupon;
import com.tian.sakura.cdd.db.manage.shop.ShopCouponManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCouponService {

    @Autowired
    private ShopCouponManage shopCouponManage;

    public PageInfo<ShopCoupon> listShopCoupon(ShopReq shopReq) {
        PageHelper.startPage(shopReq.getPageNum(), shopReq.getPageSize());
        return new PageInfo<>(shopCouponManage.listShopCoupon(shopReq));
    }

    public void insertShopCoupon(ShopCoupon shopCoupon) {
        shopCouponManage.insertSelective(shopCoupon);
    }

    public void updateShopCoupon(ShopCoupon shopCoupon) {
        shopCouponManage.updateByPrimaryKeySelective(shopCoupon);
    }

    public void deleteShopCoupon(ShopCoupon shopCoupon) {
        shopCouponManage.deleteByPrimaryKey(shopCoupon.getId());
    }
}
