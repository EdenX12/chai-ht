package com.tian.sakura.cdd.srv.service.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dict.ECouponStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.db.domain.productCoupon.ProductCoupon;
import com.tian.sakura.cdd.db.domain.shop.ShopCoupon;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.product.ProductCouponManage;
import com.tian.sakura.cdd.db.manage.shop.ShopCouponManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.db.manage.shop.vo.ShopCouponQryVo;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponAddReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponListReq;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponListRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.coupon.ShopCouponReq;

@Service
@Transactional
public class CouponService {
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private ShopCouponManage shopCouponManage;
	@Autowired
	private ProductCouponManage productCouponManage;
	
	//分页查询
	public PageInfo getCouponList(SUser user, ShopCouponListReq req) {
		ShopCouponQryVo vo = new ShopCouponQryVo();
		vo.setPageNum(req.getBody().getPageNum());
		vo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		vo.setShopId(shopId.toString());
		Integer couponState = req.getBody().getCouponStatus();
		vo.setCouponState(couponState);
		String strTodayDate = DateUtils.formatDateWithyyyy_MM_dd(new Date());
		Date todayDate = DateTime.parse(strTodayDate.concat("T00:00:01")).toDate();
		vo.setQryDate(todayDate);

		List<ShopCouponListRspBody> rspBodys = new ArrayList<>();
		List<ShopCoupon> coupons = shopCouponManage.getCouponList(vo);
		for (ShopCoupon shopCoupon : coupons) {
			ShopCouponListRspBody body = new ShopCouponListRspBody();
			body.setCouponAmount(shopCoupon.getCouponAmount());
			body.setCouponName(shopCoupon.getCouponName());
			body.setCouponQuantity(shopCoupon.getCouponQuantity());
			body.setCouponState(couponState);
			body.setCouponId(shopCoupon.getId());
			
			if (shopCoupon.getEndDate() != null) {
				body.setEndDate(shopCoupon.getEndDate().getTime());
			} else {
				body.setEndDate(0l);
			}
			if (shopCoupon.getStartDate() != null) {
				body.setStartDate(shopCoupon.getStartDate().getTime());
			} else {
				body.setStartDate(0l);
			}
			body.setUseCon(shopCoupon.getUseCon());
			body.setCouponType(shopCoupon.getUseCon());
			rspBodys.add(body);
		}

		PageInfo pageInfo = PageInfo.of(rspBodys);
		pageInfo.setList(rspBodys);
		return pageInfo;
	}
	
	//新增券
	public void addCoupon(SUser user,ShopCouponAddReq req) {
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();

		ShopCoupon shopCoupon = new ShopCoupon();
		shopCoupon.setAddTime(new Date());
		shopCoupon.setCouponAmount(req.getBody().getCouponAmount());
		shopCoupon.setCouponName(req.getBody().getCouponName());//使用门槛
		shopCoupon.setCouponQuantity(req.getBody().getCouponQuantity());
		shopCoupon.setCouponStatus(1);
		shopCoupon.setCreateUser(user.getId());
		shopCoupon.setEndDate(req.getBody().getEndDate());
		shopCoupon.setMinConsumeAmount(req.getBody().getMinConsumeAmount());
		shopCoupon.setShopId(shopId.toString());
		shopCoupon.setStartDate(req.getBody().getStartDate());
		shopCoupon.setUseCon(req.getBody().getCouponType());//券类型
		shopCoupon.setLimitDays(req.getBody().getLimitDays());//有效期天数
		shopCoupon.setProductScope(req.getBody().getProductScope());//适用产品类型
		
		shopCouponManage.insertSelective(shopCoupon);
		
		Integer productScope = req.getBody().getProductScope();
		if(productScope == 1) {//指定组
			List<String> groups = req.getBody().getGroups();
			for(String groupId:groups) {
				ProductCoupon productCoupon = new ProductCoupon();
				productCoupon.setCreateTime(new Date());
				productCoupon.setGroupOrProductId(groupId);
				productCoupon.setShopId(shopId);
				productCoupon.setCouponId(Integer.parseInt(shopCoupon.getId()));
				productCoupon.setStatus("0");
				productCouponManage.insert(productCoupon);
			}
		}else if(productScope == 2) {//指定商品
			List<String> products = req.getBody().getProducts();
			for(String productId:products) {
				ProductCoupon productCoupon = new ProductCoupon();
				productCoupon.setCreateTime(new Date());
				productCoupon.setGroupOrProductId(productId);
				productCoupon.setShopId(shopId);
				productCoupon.setCouponId(Integer.parseInt(shopCoupon.getId()));
				productCoupon.setStatus("0");
				productCouponManage.insert(productCoupon);
			}
		}
		
		
	}
	
	//作废券
	public void cancelCoupon(SUser user,ShopCouponReq req) {
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Integer couponId = req.getBody().getCouponId();
		ShopCoupon shopCoupon = shopCouponManage.selectByPrimary(couponId.toString());
		if(!shopCoupon.getShopId().equals(shopId.toString())) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR, "商铺数据不匹配");
		}
		shopCoupon =  new ShopCoupon();
		shopCoupon.setId(couponId.toString());
		shopCoupon.setCouponStatus(ECouponStatus.CANCEL.getCode());
		shopCouponManage.updateByPrimaryKeySelective(shopCoupon);
		//作废适用的商品
		productCouponManage.cancelByCouponId(shopId, couponId);
	}
	
	//券详情
	public ShopCouponListRspBody getCouponDetail(SUser user,ShopCouponReq req) {
		
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Integer couponId = req.getBody().getCouponId();
		ShopCoupon shopCoupon = shopCouponManage.selectByPrimary(couponId.toString());
		if(!shopCoupon.getShopId().equals(shopId.toString())) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR, "商铺数据不匹配");
		}
		ShopCouponListRspBody body = new ShopCouponListRspBody();
		body.setCouponAmount(shopCoupon.getCouponAmount());
		body.setCouponName(shopCoupon.getCouponName());
		body.setCouponQuantity(shopCoupon.getCouponQuantity());
		body.setLimitDays(shopCoupon.getLimitDays());
		body.setCouponId(shopCoupon.getId().toString());
		body.setEndDate(shopCoupon.getEndDate().getTime());
		body.setStartDate(shopCoupon.getStartDate().getTime());
		body.setUseCon(shopCoupon.getUseCon());
		body.setCouponType(shopCoupon.getUseCon());
		Long today = new Date().getTime();
		Integer couponState = -1;
		if(shopCoupon.getCouponStatus()==ECouponStatus.CANCEL.getCode()) {
			couponState = 3;//已作废
		}else if(body.getStartDate() > today) {//还未开始
			couponState = 0;
		}else if(body.getEndDate() < today) {//已结束
			couponState = 2;
		}else if(body.getStartDate() <= today && today <= body.getEndDate()) {
			//生效中
			couponState = 1;
		}
		body.setCouponState(couponState);
		body.setProductScope(shopCoupon.getProductScope());
		
		if(shopCoupon.getProductScope() == 1) {
			//指定组
			List<ProductCoupon> groups = productCouponManage.selectByCouponId(shopId, couponId);
			List<String> g = new ArrayList<>();
			for(ProductCoupon pc:groups) {
				g.add(pc.getGroupOrProductId());
			}
			body.setGroups(g);
		}else if(shopCoupon.getProductScope() == 2 ) {
			//指定商品
			List<ProductCoupon> products = productCouponManage.selectByCouponId(shopId, couponId);
			List<String> p = new ArrayList<>();
			for(ProductCoupon pc:products) {
				p.add(pc.getGroupOrProductId());
			}
			body.setProducts(p);
		}
		
		return body;
	}
	
	//编辑券
	public void editCoupon(SUser user,ShopCouponAddReq req) {
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Integer couponId = req.getBody().getCouponId();
		ShopCoupon shopCoupon = shopCouponManage.selectByPrimary(couponId.toString());
		if(!shopCoupon.getShopId().equals(shopId.toString())) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR, "商铺数据不匹配");
		}
		if(!shopCoupon.getId().equals(couponId.toString())) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR, "券数据不匹配");
		}
		shopCoupon = new ShopCoupon();
		shopCoupon.setId(couponId.toString());
		shopCoupon.setUpdateTime(new Date());
		shopCoupon.setCouponAmount(req.getBody().getCouponAmount());
		shopCoupon.setCouponName(req.getBody().getCouponName());//使用门槛
		shopCoupon.setCouponQuantity(req.getBody().getCouponQuantity());
		shopCoupon.setUpdateUser(user.getId());
		shopCoupon.setEndDate(req.getBody().getEndDate());
		shopCoupon.setMinConsumeAmount(req.getBody().getMinConsumeAmount());
		shopCoupon.setShopId(shopId.toString());
		shopCoupon.setStartDate(req.getBody().getStartDate());
		shopCoupon.setUseCon(req.getBody().getCouponType());//券类型
		shopCoupon.setLimitDays(req.getBody().getLimitDays());//有效期天数
		shopCoupon.setProductScope(req.getBody().getProductScope());//适用产品类型

		//作废适用的商品
		productCouponManage.cancelByCouponId(shopId, couponId);

		Integer productScope = req.getBody().getProductScope();
		if(productScope == 1) {//指定组
			List<String> groups = req.getBody().getGroups();
			for(String groupId:groups) {
				ProductCoupon productCoupon = new ProductCoupon();
				productCoupon.setCreateTime(new Date());
				productCoupon.setGroupOrProductId(groupId);
				productCoupon.setShopId(shopId);
				productCoupon.setCouponId(Integer.parseInt(shopCoupon.getId()));
				productCoupon.setStatus("0");
				productCouponManage.insert(productCoupon);
			}
		}else if(productScope == 2) {//指定商品
			List<String> products = req.getBody().getProducts();
			for(String productId:products) {
				ProductCoupon productCoupon = new ProductCoupon();
				productCoupon.setCreateTime(new Date());
				productCoupon.setGroupOrProductId(productId);
				productCoupon.setShopId(shopId);
				productCoupon.setCouponId(Integer.parseInt(shopCoupon.getId()));
				productCoupon.setStatus("0");
				productCouponManage.insert(productCoupon);
			}
		}

		
		shopCouponManage.updateByPrimaryKeySelective(shopCoupon);
	}
}
