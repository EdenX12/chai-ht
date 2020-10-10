package com.tian.sakura.cdd.db.manage.shop;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.dao.shop.ShopCouponMapper;
import com.tian.sakura.cdd.db.domain.shop.ShopCoupon;
import com.tian.sakura.cdd.db.manage.shop.vo.ShopCouponQryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCouponManage extends AbstractSingleManage<ShopCoupon,String> {

    @Autowired
    private ShopCouponMapper shopCouponMapper;

    @Override
    protected AbstractSingleMapper<ShopCoupon, String> getSingleMapper() {
        return shopCouponMapper;
    }

    public List<ShopCoupon> listShopCoupon(ShopReq shopReq) {
        return shopCouponMapper.selectShopCouponByShopId(shopReq.getId());
    }
    
    public List<ShopCoupon> getCouponList(ShopCouponQryVo vo){
    	return shopCouponMapper.getCouponPage(vo);
    }
}
