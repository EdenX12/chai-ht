package com.tian.sakura.cdd.srv.web.product;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tian.sakura.cdd.srv.service.activityProduct.ActivityProductService;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityReq;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("activityProduct")
@Api(value = "活动商品相关")
public class ActivityProductController {
	@Autowired
	private ActivityProductService activityProductService;
	
	@ApiOperation(value = "活动商品列表")
    @PostMapping("/getActPrdList")
	public PageInfo<ProductRspBody> getActPrdList(@RequestBody ActivityReq activityReq){
		return activityProductService.getProductListByActityId(activityReq);
	}
}
