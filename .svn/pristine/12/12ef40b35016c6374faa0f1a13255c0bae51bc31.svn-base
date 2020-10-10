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
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.shop.ShopDataService;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopDataReq;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopDataRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopTodayDataReq;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopTodayDataRspBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("shopData")
@Api("商铺数据统计")
public class ShopDataController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShopDataService shopDataService;
	
	@ApiOperation("今日数据/历史数据")
    @PostMapping("/todayData")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopTodayDataRspBody getShopTodayData(@RequestBody ShopTodayDataReq req) {
		return shopDataService.getShopTodayData(LoginUserThreadLocal.getLoginUser(),req);
	}
	
	@ApiOperation("店铺数据")
    @PostMapping("/shopData")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopDataRspBody getShopData(@RequestBody ShopDataReq req) {
		return shopDataService.getShopData(LoginUserThreadLocal.getLoginUser(),req);
	}
	
}
