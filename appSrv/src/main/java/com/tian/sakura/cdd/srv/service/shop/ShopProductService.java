package com.tian.sakura.cdd.srv.service.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dict.EProductStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.dao.productImg.ProductImgMapper;
import com.tian.sakura.cdd.db.dao.productType.ProductTypeMapper;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.productGroup.ProductGroup;
import com.tian.sakura.cdd.db.domain.productImg.ProductImg;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;
import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductReviewStatManage;
import com.tian.sakura.cdd.db.manage.product.vo.ShopProductVo;
import com.tian.sakura.cdd.db.manage.product.vo.ShopRecommendPrd;
import com.tian.sakura.cdd.db.manage.shop.ShopGroupManage;
import com.tian.sakura.cdd.db.manage.shop.ShopPrdGroupManage;
import com.tian.sakura.cdd.db.manage.shop.ShopReturnManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.task.TaskAwardService;
import com.tian.sakura.cdd.srv.service.task.TaskLineService;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductDetailReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductDetailReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductImgReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductDeleteReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductQryReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;
import com.tian.sakura.video.service.core.OSSCore;

/**
 * 店铺商品管理
 * 
 * @author liuhg
 *
 */
@Service
@Transactional
public class ShopProductService {
	@Autowired
	private ProductManage productManage;
	@Autowired
	private ShopPrdGroupManage shopPrdGroupManage;
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private OSSCore OSSCore;
	@Autowired
	private ProductImgMapper prdImgMapper;

	@Autowired
	private ShopGroupManage shopGroupManage;
	@Autowired
	private ProductTypeMapper productTypeMapper;

	@Autowired
	private ShopReturnManage shopReturnManage;
	@Autowired
	private ProductReviewStatManage productReviewStatManage;

	@Autowired
	private TaskAwardService taskAwardService;
	@Autowired
	private TaskLineService taskLineService;
	@Autowired
	private ParamsManage paramsManage;


	// 产生商品
	public ShopProductRspBody createProduct(String userId, ShopProductReq req) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();

		ShopProductReqBody reqBody = req.getBody();

		Product prd = new Product();
		prd.setProductName(reqBody.getProductName());
		prd.setTypeId(reqBody.getProductType1Id());
		prd.setType2Id(reqBody.getProductType2Id());
		prd.setDeliveryType(reqBody.getDeliveryType());
		prd.setExpressFee(reqBody.getFreight());
		prd.setProductPrice(reqBody.getDiscountPrice());// 折后价格
		prd.setExpressFee(reqBody.getFreight());
		prd.setDiscountRate(reqBody.getDiscountRate());
		prd.setScribingPrice(reqBody.getPrice());// 划线价格
		prd.setStockNumber(reqBody.getQuantity());
		prd.setStartDate(reqBody.getStartDate());
		prd.setShopId(shopId.toString());
		prd.setProductStatus(0);

		String productId = req.getBody().getProductId();
		if (productId == null || productId.equals("")) {
			productId = IdGenUtil.uuid();
			prd.setId(productId);
			productManage.insert(prd);
		} else {
			prd.setId(productId);
			productManage.updateByPrimaryKeySelective(prd);
		}
		// 分组
		Integer groupId = reqBody.getGroupId();
		ProductGroup prdGroup = new ProductGroup();
		prdGroup.setShopId(shopId);
		prdGroup.setGroupId(groupId);
		prdGroup.setProductId(productId);
		shopPrdGroupManage.insert(prdGroup);

		ShopProductRspBody rspBody = new ShopProductRspBody();
		rspBody.setProductId(productId);

		return rspBody;

	}

	// 上传商品图片
	public ShopProductRspBody uploadPrdImg(String userId, ProductImgReqBody body) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();

		List<ProductImg> pis = null;
		String productId = body.getProductId();
		if (productId == null || productId.equals("")) {
			productId = IdGenUtil.uuid();
			Product prd = new Product();
			prd.setId(productId);
			prd.setShopId(shopId.toString());
			productManage.insertSelective(prd);
		} else {
			pis = prdImgMapper.qryPrdImg(productId);
		}
		String productImg = body.getProductImg();
		Integer imgType = body.getImgType();
		String imgUrl = OSSCore.upload(new MockMultipartFile(productId, Base64Utils.decodeFromString(productImg)));
		ProductImg prdImg = new ProductImg();
		prdImg.setProductId(productId);
		prdImg.setImgType(imgType);
		prdImg.setImgUrl(imgUrl);
		prdImg.setShopId(shopId);
		prdImg.setCreateTime(new Date());

		int imgType1 = 0;
		int imgType2 = 0;
		int position = -1;

		if (pis == null || pis.size() == 0) {
			imgType1 = 0;
			imgType2 = 0;
		} else {
			for (ProductImg pi : pis) {
				if (pi.getImgType() == 1) {
					imgType1++;
					position++;
				} else if (pi.getImgType() == 2) {
					imgType2++;
				}
			}
		}

		if (imgType == 1) {// 缩略图
			if (imgType1 > 0) {
				// 更新缩略图
				Integer idx = pis.get(position).getId();
				prdImg.setId(idx);
				prdImgMapper.updateByPrimaryKey(prdImg);
			} else {
				// 插入
				prdImgMapper.insert(prdImg);
			}
			Product prd = new Product();
			prd.setId(productId);
			prd.setProductImg(prdImg.getImgUrl());
			productManage.updateByPrimaryKeySelective(prd);// 更新主表缩略图
		} else if (imgType == 2) {// 主题图
			if (imgType2 >= 4) {
				// 更新最早一张主题图
				Integer idx = pis.get(0).getId();
				prdImg.setId(idx);
				prdImgMapper.updateByPrimaryKey(prdImg);
			} else {
				// 插入
				prdImgMapper.insert(prdImg);
			}

		}

		ShopProductRspBody rspBody = new ShopProductRspBody();
		rspBody.setProductId(productId);
		rspBody.setImgUrl(imgUrl);

		return rspBody;
	}

	// 商品详情
	public ShopProductRspBody productDetail(String userId, ProductDetailReqBody body) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();

		Product prd = new Product();
		prd.setShopId(shopId.toString());
		prd.setProductDetail(body.getProductDetail());

		String productId = body.getProductId();

		if (productId == null || productId.equals("")) {

			productId = IdGenUtil.uuid();
			prd.setId(productId);
			productManage.insert(prd);
		} else {
			prd.setId(productId);
			productManage.updateByPrimaryKeySelective(prd);
		}

		ShopProductRspBody rspBody = new ShopProductRspBody();
		rspBody.setProductId(productId);

		return rspBody;

	}

	// 查询商品列表
	public List<ShopProductListRspBody> productList(String userId, ShopProductListReqBody reqBody) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Integer productStatus = reqBody.getProductStatus();
		List<ShopProductListRspBody> rspList = new ArrayList<>();
		List<ShopProductVo> shopProductVos = productManage.getShopProductList(shopId.toString(), productStatus);
		for (ShopProductVo spv : shopProductVos) {
			ShopProductListRspBody rspBody = new ShopProductListRspBody();
			BeanUtils.copyProperties(spv, rspBody);
			rspList.add(rspBody);
		}
		return rspList;
	}

	// 删除商品
	public ShopProductRspBody deleteProduct(String userId, ShopProductDeleteReqBody reqBody) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		String productId = reqBody.getProductId();
		if (productId == null || productId.equals("")) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		Product prd = productManage.selectByPrimary(productId);
		if (prd == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		Integer productStatus = prd.getProductStatus();
		if (productStatus != 0) {
			throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, "删除商品");
		}

		prd.setDeleteFlag(1);// 置删除标志
		productManage.updateByPrimaryKeySelective(prd);
		OSSCore.delete(prd.getProductImg());
		ShopProductRspBody rspBody = new ShopProductRspBody();
		rspBody.setProductId(productId);

		return rspBody;

	}

	// 编辑商品
	public ShopProductRspBody editProduct(String userId, ShopProductReqBody reqBody) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		String productId = reqBody.getProductId();
		if (productId == null || productId.equals("")) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		Product prd1 = productManage.selectByPrimary(productId);
		if (prd1 == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		if (prd1.getProductStatus() != EProductStatus.TO_BE_PUBLISH.getCode()) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品已上架，不允许修改");
		}

		Product prd = new Product();
		prd.setId(productId);
		prd.setProductName(reqBody.getProductName());
		prd.setTypeId(reqBody.getProductType1Id());
		prd.setType2Id(reqBody.getProductType2Id());
		prd.setDeliveryType(reqBody.getDeliveryType());
		prd.setExpressFee(reqBody.getFreight());
		prd.setProductPrice(reqBody.getDiscountPrice());// 折后价格
		prd.setExpressFee(reqBody.getFreight());
		prd.setDiscountRate(reqBody.getDiscountRate());
		prd.setScribingPrice(reqBody.getPrice());// 划线价格
		prd.setStockNumber(reqBody.getQuantity());
		prd.setStartDate(reqBody.getStartDate());
		prd.setShopId(shopId.toString());
		prd.setProductStatus(0);
		productManage.updateByPrimaryKeySelective(prd);

		// 分组
		Integer groupId = reqBody.getGroupId();
		// 删除组内商品
		shopPrdGroupManage.deleteByGroupId(groupId);
		// 插入
		ProductGroup prdGroup = new ProductGroup();
		prdGroup.setShopId(shopId);
		prdGroup.setGroupId(groupId);
		prdGroup.setProductId(productId);
		shopPrdGroupManage.insert(prdGroup);

		ShopProductRspBody rspBody = new ShopProductRspBody();
		rspBody.setProductId(productId);

		return rspBody;

	}

	// 查询商品
	public ShopProductRspBody qryProduct(String userId, ShopProductQryReq req) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		String productId = req.getBody().getProductId();
		if (productId == null || productId.equals("")) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		Product prd = productManage.selectByPrimary(productId);
		if (prd == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		ShopProductRspBody rspBody = new ShopProductRspBody();
		rspBody.setDeliveryType(prd.getDeliveryType());
		rspBody.setDiscountPrice(prd.getProductPrice());
		rspBody.setDiscountRate(prd.getDiscountRate());
		rspBody.setFreight(prd.getExpressFee());
		// 查询该商品所属分组
		List<ShopGroup> shopGroups = shopGroupManage.qryByPrdId(shopId, productId);
		List<Map> maps = new ArrayList<>();
		for (ShopGroup sg : shopGroups) {
			Map map = new HashMap();
			map.put("id", sg.getId());
			map.put("groupName", sg.getGroupName());
			maps.add(map);
		}
		rspBody.setGroups(maps);

		rspBody.setImgUrl(prd.getProductImg());
		rspBody.setPrdDetail(prd.getProductDetail());

		List<ProductImg> pis = prdImgMapper.qryPrdImg(productId);
		List<String> imgs = new ArrayList<>();
		for (ProductImg pi : pis) {
			imgs.add(pi.getImgUrl());
		}
		rspBody.setPrdImgList(imgs);

		rspBody.setPrice(prd.getScribingPrice());
		rspBody.setProductId(prd.getId());
		rspBody.setProductName(prd.getProductName());
		// 关注人数
		int totalFocus = productReviewStatManage.totalFocus(productId);
		rspBody.setFocusCnt(totalFocus);
		BigDecimal productTotalCommission = prd.getTotalReward();
		BigDecimal buyerCommission = BigDecimal.ZERO;// 买家立返佣金
		BigDecimal sameGroupCommission = BigDecimal.ZERO;// 同组躺赢佣金

		if (productTotalCommission.compareTo(BigDecimal.ZERO) != 0) {
			// 买家立返
			buyerCommission = taskAwardService.buyerCommission(productTotalCommission);
			// 计算同组最低躺赢金
			int taskNumber = prd.getTaskNumber();// 该商品每条任务线上的最大任务数
			if (taskNumber > 0) {
				sameGroupCommission = taskAwardService.sameGroupCommission(productTotalCommission, taskNumber);
			}
		}
		rspBody.setSuccessReward(buyerCommission);			// 买家立返
		rspBody.setEveryReward(sameGroupCommission);			// 计算同组最低躺赢金
		
		int userCount = taskLineService.userCount(productId); //参与拆家
		rspBody.setUserCount(userCount);

		if (prd.getTypeId() != null) {
			ProductType pt = productTypeMapper.selectByPrimaryKey(Integer.valueOf(prd.getTypeId()));
			if (pt != null) {
				rspBody.setProductType1Name(pt.getTypeName());
			}
			rspBody.setProductType1Id(prd.getTypeId());

		}
		if (prd.getType2Id() != null) {
			ProductType pt2 = productTypeMapper.selectByPrimaryKey(Integer.valueOf(prd.getType2Id()));
			if (pt2 != null) {
				rspBody.setProductType2Name(pt2.getTypeName());
			}
			rspBody.setProductType2Id(prd.getType2Id());

		}

		rspBody.setQuantity(prd.getStockNumber());
		rspBody.setStartDate(prd.getStartDate());

		// 自提货地址
		List<ShopReturn> sr = shopReturnManage.qryShopReturnByShopId(shopId, 2);
		List<AddressBody> l = new ArrayList<>();
		for (ShopReturn shopReturn : sr) {
			AddressBody addressBody = new AddressBody();
			addressBody.setAddress(shopReturn.getAddress());
			addressBody.setCityId(shopReturn.getCityId());
			addressBody.setCityName(shopReturn.getCity());
			addressBody.setCountryId(shopReturn.getCountryId());
			addressBody.setCountryName(shopReturn.getCountry());
			addressBody.setProvinceId(shopReturn.getProvinceId());
			addressBody.setProvinceName(shopReturn.getProvince());
			l.add(addressBody);
		}
		rspBody.setSelfAddressList(l);
		rspBody.setProductStatus(prd.getProductStatus());
		return rspBody;
	}

	// 商品上架/下架
	public void productOnOff(String userId, ShopProductQryReq req) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		String productId = req.getBody().getProductId();
		if (productId == null || productId.equals("")) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		Product prd = productManage.selectByPrimary(productId);
		if (prd == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该商品不存在");
		}
		prd.setProductStatus(req.getBody().getProductStatus());
		productManage.updateByPrimaryKeySelective(prd);

	}
	
	//推荐商铺商品
	public List<ShopRecommendPrd> getShopRecommendPrd(){
		String shopId = paramsManage.getValueByKey(GlobalConstants.SHOP_RECOMMEND);
		return productManage.shopRecommendPrd(Integer.parseInt(shopId));
	}
}
