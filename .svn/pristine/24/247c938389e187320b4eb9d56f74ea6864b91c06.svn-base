package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.domain.shop.ShopCoupon;
import com.tian.sakura.video.service.auth.ShopCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ShopCouponService shopCouponService;

    @PostMapping("/listShopCoupon")
    public ResultDto listShopCoupon(@RequestBody ShopReq shopReq) {
        return ResultDto.success().setObj(shopCouponService.listShopCoupon(shopReq));
    }

    @PostMapping("/insertShopCoupon")
    public ResultDto insertShopCoupon(@RequestBody ShopCoupon shopCoupon) {
        shopCouponService.insertShopCoupon(shopCoupon);
        return ResultDto.success();
    }

    @PostMapping("/updateShopCoupon")
    public ResultDto updateShopCoupon(@RequestBody ShopCoupon shopCoupon) {
        shopCouponService.updateShopCoupon(shopCoupon);
        return ResultDto.success();
    }

    @PostMapping("/deleteShopCoupon")
    public ResultDto deleteShopCoupon(@RequestBody ShopCoupon shopCoupon) {
        shopCouponService.deleteShopCoupon(shopCoupon);
        return ResultDto.success();
    }
}
