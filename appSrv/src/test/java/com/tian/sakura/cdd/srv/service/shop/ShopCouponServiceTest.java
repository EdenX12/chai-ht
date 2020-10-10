package com.tian.sakura.cdd.srv.service.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponAddReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponAddReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponListReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponListReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopCouponServiceTest {
	@Autowired
	private CouponService shopCouponService;
//	@Test
//	public void list() {
//		SUser user = new SUser();
//		ShopCouponListReq req = new ShopCouponListReq();
//		ShopCouponListReqBody body =  new ShopCouponListReqBody();
//		req.setBody(body);
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		body.setCouponStatus(1);
//		Object obj = shopCouponService.getCouponList(user, req);
//		System.out.println(JSON.toJSONString(obj));
//	}
//	@Test
//	public void add() {
//		SUser user = new SUser();
//		ShopCouponAddReq req = new ShopCouponAddReq();
//		ShopCouponAddReqBody body =  new ShopCouponAddReqBody();
//		req.setBody(body);
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		body.setCouponAmount(BigDecimal.valueOf(200));
//		body.setCouponName("满100减5");
//		body.setCouponQuantity(300);
//		body.setCouponType(1);
//		body.setMinConsumeAmount(BigDecimal.valueOf(50));
//		body.setStartDate(new Date());
//		body.setEndDate(new Date());
//		body.setCouponType(3);
//		body.setLimitDays(15);
//		body.setProductScope(2);
//		List<String> groups = new ArrayList<>();
//		groups.add("0");
//		groups.add("1");
//		List<String> products = new ArrayList<>();
//		products.add("3");
//		products.add("4");
//		body.setGroups(groups);
//		body.setProducts(products);
//		shopCouponService.addCoupon(user, req);
//	}

//	@Test
//	public void cancel() {
//		SUser user = new SUser();
//		ShopCouponReq req = new ShopCouponReq();
//		ShopCouponReqBody body =  new ShopCouponReqBody();
//		req.setBody(body);
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		body.setCouponId(13);
//		shopCouponService.cancelCoupon(user, req);
//	}

	@Test
	public void qryDetail() {
		SUser user = new SUser();
		ShopCouponReq req = new ShopCouponReq();
		ShopCouponReqBody body =  new ShopCouponReqBody();
		req.setBody(body);
		user.setId("3dc4db16e90848e18bcb91553f35eff7");
		body.setCouponId(16);
		Object obj = shopCouponService.getCouponDetail(user, req);
		System.out.println(JSON.toJSONString(obj));
	}

//	@Test
//	public void edit() {
//		SUser user = new SUser();
//		ShopCouponAddReq req = new ShopCouponAddReq();
//		ShopCouponAddReqBody body =  new ShopCouponAddReqBody();
//		req.setBody(body);
//		user.setId("3dc4db16e90848e18bcb91553f35eff7");
//		body.setCouponAmount(BigDecimal.valueOf(200));
//		body.setCouponName("满100减50");
//		body.setCouponQuantity(500);
//		body.setCouponType(2);
//		body.setMinConsumeAmount(BigDecimal.valueOf(150));
//		body.setStartDate(new Date());
//		body.setEndDate(new Date());
//		body.setCouponType(1);
//		body.setLimitDays(20);
//		body.setCouponId(12);
//		body.setProductScope(2);
//		List<String> groups = new ArrayList<>();
//		groups.add("0");
//		groups.add("1");
//		List<String> products = new ArrayList<>();
//		products.add("13");
//		products.add("14");
//		body.setGroups(groups);
//		body.setProducts(products);
//
//		shopCouponService.editCoupon(user, req);
//	}

}
