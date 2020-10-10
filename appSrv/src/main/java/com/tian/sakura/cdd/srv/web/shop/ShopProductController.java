package com.tian.sakura.cdd.srv.web.shop;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.service.shop.ShopGroupService;
import com.tian.sakura.cdd.srv.service.shop.ShopProductService;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpReq;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.DeliveryTypeRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductDetailReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductGroupReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductGroupReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductGroupRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductImgReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopDeleteGroupProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopMoveGroupProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductDeleteReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductListRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductQryReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductQryReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商铺商品相关
 * @author liuhg
 *
 */
@RestController
@RequestMapping("shopProduct")
@Api("商铺-商品管理")
public class ShopProductController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShopGroupService shopGroupService;
	@Autowired
	private ShopProductService shopProductService;
	@Autowired
	private ParamsService paramsService;
	
	@ApiOperation("查询商铺商品分组")
    @PostMapping("/qryShopGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public List<ShopGroupRspBody> qryShopGroup(){
		SUser user = LoginUserThreadLocal.getLoginUser();
		return shopGroupService.qryShopGroup(user.getId());
	}

	@ApiOperation("创建分组")
    @PostMapping("/createShopGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopGroupRspBody createShopGroup(@RequestBody ShopGroupReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopGroupService.createShopGroup(userId, req.getBody());
	}
	@ApiOperation("创建分组内的商品")
    @PostMapping("/createPrdGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ProductGroupRspBody createPrdGroup(@RequestBody ProductGroupReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopGroupService.createPrdGroup(userId, req.getBody());
	}

	@ApiOperation("新增商品")
    @PostMapping("/createProduct")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopProductRspBody createProduct(@RequestBody ShopProductReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.createProduct(userId,req);
	}

	@ApiOperation("物流配送方式查询")
    @PostMapping("/qryDeliveryType")
    public List<DeliveryTypeRspBody> qryDeliveryType(){
		String deliveryType =(String) paramsService.getValue("delivery_type");
		if(deliveryType == null || deliveryType.equals("")) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "没有维护物流配送方式参数");
		}
		String[] sts = StringUtils.split(deliveryType, "|");
		List<DeliveryTypeRspBody> l = new ArrayList<>();
		for(String s:sts) {
			DeliveryTypeRspBody body = new DeliveryTypeRspBody();
			String typeId = StringUtils.split(s,"-")[0];
			String typeName = StringUtils.split(s,"-")[1];
			body.setTypeId(Integer.parseInt(typeId));
			body.setTypeName(typeName);
			l.add(body);
		}
		return l;
	}

	@ApiOperation("/上传商品图片")
    @PostMapping("/uploadPrdImg")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopProductRspBody uploadProductImg(@RequestBody ProductImgReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.uploadPrdImg(userId,req.getBody());
	}

	@ApiOperation("/商品详情")
    @PostMapping("/prdDetail")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopProductRspBody productDetail(@RequestBody ProductDetailReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.productDetail(userId,req.getBody());
	}

	@ApiOperation("/商品列表")
    @PostMapping("/prdList")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public List<ShopProductListRspBody> productList(@RequestBody ShopProductListReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.productList(userId,req.getBody());
	}
	@ApiOperation("编辑商品")
    @PostMapping("/editProduct")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopProductRspBody editProduct(@RequestBody ShopProductReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.editProduct(userId,req.getBody());
	}
	@ApiOperation("删除商品")
    @PostMapping("/deleteProduct")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopProductRspBody deleteProduct(@RequestBody ShopProductDeleteReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.deleteProduct(userId,req.getBody());
	}

	@ApiOperation("商品查询")
    @PostMapping("/qryProduct")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public ShopProductRspBody qryProduct(@RequestBody ShopProductQryReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		return shopProductService.qryProduct(userId,req);
	}

	@ApiOperation("商品上架/下架")
    @PostMapping("/productOnOff")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void productOnOff(@RequestBody ShopProductQryReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		shopProductService.productOnOff(userId,req);
	}

	@ApiOperation("删除分组")
    @PostMapping("/deleteShopGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void deleteShopGroup(@RequestBody ShopGroupReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		shopGroupService.deleteShopGroup(userId, req.getBody());
	}
	@ApiOperation("分组重命名")
    @PostMapping("/renameShopGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void renameShopGroup(@RequestBody ShopGroupReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		shopGroupService.renameShopGroup(userId, req.getBody());
	}
	@ApiOperation("删除组内商品")
    @PostMapping("/deleteProductInGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void deletePrdInGroup(@RequestBody ShopDeleteGroupProductReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		shopGroupService.deletePrdInGroup(userId, req);
	}
	@ApiOperation("移动组内商品")
    @PostMapping("/moveProductInGroup")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
	public void movePrdInGroup(@RequestBody ShopMoveGroupProductReq req) {
		SUser user = LoginUserThreadLocal.getLoginUser();
		String userId = user.getId();
		shopGroupService.movePrdInGroup(userId, req);
	}

}
