package com.tian.sakura.cdd.srv.service.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductReviewStatManage;
import com.tian.sakura.cdd.db.manage.product.vo.ShopGroupProductVo;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.srv.service.task.TaskAwardService;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupProductReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderGroupProductRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopReqBody;

@Service
public class ShopHeaderService {
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private ShopManage shopManage;
	@Autowired
	private ProductManage productManage;
	@Autowired
	private TaskAwardService taskAwardService;
	@Autowired
	private TaskLineManage taskLineManage;
	@Autowired
	private ProductReviewStatManage productReviewStatManage;
	@Autowired
	private ShopGroupService shopGroupService;
	@Autowired
	private OrderDetailManage orderDetailManage;
	

	public ShopHeaderRspBody getShopHeaderTop(String userId) {
		// 根据用户查商铺
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		// 查商铺
		Integer shopId = ss.getShopId();
		Shop shop = shopManage.selectByPrimary(shopId);
		// 商铺商品库存数量
		Integer productCnt = productManage.getShopProductCnt(shopId);
		// 关注人数
		Integer productFollowCnt = productManage.getShopProductFollwCnt(shopId);

		ShopHeaderRspBody rspBody = new ShopHeaderRspBody();
		rspBody.setShopName(shop.getShopName());
		rspBody.setShopId(shopId);
		rspBody.setShopLogo(shop.getShopLogo());
		rspBody.setProductCnt(productCnt);
		rspBody.setFollowCnt(productFollowCnt);
		return rspBody;
	}

	// 商铺首页-首页页签
	public PageInfo getShopHeader(String userId, ShopReqBody body) {
		Integer shopId = body.getShopId();
		List<ProductRspBody> rspBodyList ;
		Integer productType = 2; // 正常商品
		if (shopId == null) {// 查询登录人所拥有的店铺
			rspBodyList = getShopPrd(userId, productType, null,null,body,null);
		}else {
			rspBodyList = getShopPrd(userId, productType, null,shopId,body,null);
		}
		PageInfo pageInfo =  PageInfo.of(rspBodyList);
		pageInfo.setList(rspBodyList);
		return pageInfo;
	}
	// 商铺首页-商品模糊查询
	public PageInfo productSearch(String userId, ShopReqBody body) {
		Integer shopId = body.getShopId();
		List<ProductRspBody> rspBodyList ;
		String productName = body.getProductName();
		if(StringUtils.isAllBlank(productName)) {
			productName = null;
		}
		if (shopId == null) {// 查询登录人所拥有的店铺
			rspBodyList = getShopPrd(userId, null, null,null,body,productName);
		}else {
			rspBodyList = getShopPrd(userId, null, null,shopId,body,productName);
		}
		PageInfo pageInfo =  PageInfo.of(rspBodyList);
		pageInfo.setList(rspBodyList);
		return pageInfo;
	}

	// 商铺首页-新品页签
	public PageInfo getShopHeaderNewProduct(String userId,ShopReqBody body) {
		Integer shopId = body.getShopId();
		List<ProductRspBody> rspBodyList ;
		Integer productType = 1; // 新手商品
		if (shopId == null) {// 查询登录人所拥有的店铺
			rspBodyList = getShopPrd(userId, productType, null,null,body,null);
		}else {
			rspBodyList = getShopPrd(userId, productType, null,shopId,body,null);
		}
		PageInfo pageInfo =  PageInfo.of(rspBodyList);
		pageInfo.setList(rspBodyList);
		return pageInfo;
	}

	// 商铺首页-组内商品列表
	public List<ProductRspBody> getGroupPrd(String userId,ShopReqBody body) {
		Integer shopId = body.getShopId();
		Integer groupId = body.getGroupId();
		List<ProductRspBody> rspBodyList = getShopPrd(userId, null, groupId,shopId,body,null);
		return rspBodyList;
	}

	private List<ProductRspBody> getShopPrd(String userId, Integer productType, Integer groupId, Integer shopId,
			ShopReqBody body,String productName) {
		ShopGroupProductVo sgpv = new ShopGroupProductVo();
		sgpv.setPageNum(body.getPageNum());
		sgpv.setPageSize(body.getPageSize());
		PageHelper.startPage(sgpv.getPageNum(), sgpv.getPageSize());
		if(shopId == null) {
			// 根据用户查商铺
			ShopUser ss = shopUserManage.findByUserId(userId);
			if (ss == null) {
				throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
			}
		   shopId = ss.getShopId();
		}
		
		List<ProductRspBody> rspBodyList = new ArrayList<>();
		// 查询商铺的商品
		List<Product> ls;
		if(productName != null) { //模糊查询商品
			sgpv.setShopId(shopId);
			sgpv.setProductName(productName);
			ls = productManage.searchShopProduct(sgpv);
		}
		else if (productType == null && groupId != null) {//查询组内所有商品
			sgpv.setShopId(shopId);
			sgpv.setGroupId(groupId);
			ls = productManage.getGroupProduct(sgpv);
		} else {//查询正常商品或新品
			sgpv.setShopId(shopId);
			sgpv.setProductType(productType);
			ls = productManage.getShopNormalProduct(sgpv);
		}

		for (Product product : ls) {
			ProductRspBody rspBody = new ProductRspBody();
			rspBody.setProductId(product.getId());// 商品id
			rspBody.setProductImg(product.getProductImg());// 商品缩略图
			rspBody.setProductPrice(product.getProductPrice());// 商品价格
			rspBody.setProductName(product.getProductName());// 商品名称
			rspBody.setProductStatus(product.getProductStatus());// 商品状态
			rspBody.setTaskPrice(product.getTaskPrice());// 任务金
			BigDecimal buyerCommission = BigDecimal.ZERO;// 买家立返佣金
			BigDecimal productTotalCommission = product.getTotalReward();// 获取商品总佣金
			buyerCommission = taskAwardService.buyerCommission(productTotalCommission);
			rspBody.setSuccessReward(buyerCommission); // 买家立返
			int totalTaskNumber = product.getTaskNumber();// 每条任务线的任务数
			BigDecimal sameGroupCommission = BigDecimal.ZERO;// 同组躺赢佣金
			if (totalTaskNumber > 0) {
				sameGroupCommission = taskAwardService.sameGroupCommission(productTotalCommission, totalTaskNumber);
			}
			rspBody.setEveryReward(sameGroupCommission);// 任务躺赢

			// 已拆满任务线数
			int totalNumber = 0;
			int lineStatus = 1;// 已拆满
			int settleStatus = 0;// 结算状态未完成
			totalNumber = taskLineManage.getTaskLineByStatus(product.getId(), lineStatus, settleStatus);
			rspBody.setTotalNumber(totalNumber);// 已拆满任务线数

			// 参与拆家人数
			int userCnt = taskLineManage.userCount(product.getId());
			rspBody.setUserCount(userCnt);

			// 关注人数
			int totalFocus = productReviewStatManage.totalFocus(product.getId());
			rspBody.setTotalFocus(totalFocus);

			// 当前未满任务线上的份数
			int receivedTask = taskLineManage.receivedTaskCntByProductId(product.getId());
			rspBody.setReceivedTask(receivedTask);
			
			//销量
			Integer sellQuantity = 0;
			Map map = orderDetailManage.getSellQuantityByPrdId(product.getId());
			if(map != null && map.get("cnt") != null) {
				BigDecimal tmp = (BigDecimal) map.get("cnt");
				sellQuantity =tmp.intValue();
			}
			rspBody.setSellQuantity(sellQuantity);
			
			//库存
			rspBody.setStockQuantity(product.getStockNumber());

			rspBodyList.add(rspBody);
		}
		
		return rspBodyList;
	}
}
