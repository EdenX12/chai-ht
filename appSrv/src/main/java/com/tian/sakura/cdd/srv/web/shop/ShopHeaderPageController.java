package com.tian.sakura.cdd.srv.web.shop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.shop.ShopHeaderService;
import com.tian.sakura.cdd.srv.service.shop.ShopProductService;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderGroupProductRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderRspBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("shopHeaderPage")
@Api("商铺-首页")
public class ShopHeaderPageController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShopHeaderService shopHeaderService;
	

	@ApiOperation("商铺首页Top区域")
    @PostMapping("/shopHeaderTop")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopHeaderRspBody getShopHeaderTop(@RequestBody ShopHeaderReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopHeaderService.getShopHeaderTop(userId);
	}

	@ApiOperation("商铺首页-首页页签")
    @PostMapping("/shopHeader")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo getShopHeader(@RequestBody ShopHeaderReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopHeaderService.getShopHeader(userId,req.getBody());
	}

	@ApiOperation("商铺首页-新品页签")
    @PostMapping("/shopHeaderNewProduct")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo getShopHeaderNewProduct(@RequestBody ShopHeaderReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopHeaderService.getShopHeaderNewProduct(userId,req.getBody());
	}

	@ApiOperation("商铺首页-查询组内商品")
    @PostMapping("/shopHeaderGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public List<ProductRspBody> getShopHeaderGroup(@RequestBody ShopHeaderReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopHeaderService.getGroupPrd(userId,req.getBody());
	}
	@ApiOperation("商铺首页-商品搜索")
    @PostMapping("/prdSearch")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public PageInfo productSearch(@RequestBody ShopHeaderReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopHeaderService.productSearch(userId,req.getBody());
	}

}
