package com.tian.sakura.cdd.srv.web.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.shop.CouponService;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponAddReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponListReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponListRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("shopCoupon")
@Api("商铺-优惠券")
public class ShopCouponController {
	@Autowired
	private CouponService shopCouponService;
	
	@ApiOperation("优惠券列表")
    @PostMapping("/list")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo getCouponList(@RequestBody ShopCouponListReq req) {
		return shopCouponService.getCouponList(LoginUserThreadLocal.getLoginUser(),req);
	}
	
	@ApiOperation("新增优惠券")
    @PostMapping("/add")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void addCoupon(@RequestBody ShopCouponAddReq req) {
		 shopCouponService.addCoupon(LoginUserThreadLocal.getLoginUser(),req);
	}

	@ApiOperation("优惠券作废")
    @PostMapping("/cancel")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void cancelCoupon(@RequestBody ShopCouponReq req) {
		 shopCouponService.cancelCoupon(LoginUserThreadLocal.getLoginUser(),req);
	}

	@ApiOperation("优惠券详情")
    @PostMapping("/detail")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopCouponListRspBody getCouponDetail(@RequestBody ShopCouponReq req) {
		 return shopCouponService.getCouponDetail(LoginUserThreadLocal.getLoginUser(),req);
	}

	@ApiOperation("编辑优惠券")
    @PostMapping("/edit")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void editCoupon(@RequestBody ShopCouponAddReq req) {
		 shopCouponService.editCoupon(LoginUserThreadLocal.getLoginUser(),req);
	}

}
