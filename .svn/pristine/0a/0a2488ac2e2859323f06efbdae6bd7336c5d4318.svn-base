package com.tian.sakura.cdd.srv.web.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.shop.MyShopService;
import com.tian.sakura.cdd.srv.web.shop.dto.MyShopReq;
import com.tian.sakura.cdd.srv.web.shop.dto.MyShopRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderRspBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("myShop")
@Api("我的商铺")

public class MyShopController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MyShopService myShopService;
	
	@ApiOperation("二维码推广")
    @PostMapping("/getQrCode")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopHeaderRspBody getQrCode(@RequestBody MyShopReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return myShopService.getQrCode(userId);
	}
	
	@ApiOperation("账户与订单")
    @PostMapping("/shopAccountOrder")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public MyShopRspBody getShopAccountOrder(@RequestBody MyShopReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return myShopService.getShopAccountOrder(userId,req);
	}
	
}
