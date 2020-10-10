package com.tian.sakura.cdd.srv.service.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.productGroup.ProductGroup;
import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.manage.shop.ShopGroupManage;
import com.tian.sakura.cdd.db.manage.shop.ShopPrdGroupManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductGroupReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductGroupRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopDeleteGroupProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopGroupRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopMoveGroupProductReq;

/**
 * 店铺商品分组
 * 
 * @author liuhg
 *
 */
@Service
@Transactional
public class ShopGroupService {
	@Autowired
	private ShopGroupManage shopGroupManage;
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private ShopPrdGroupManage shopPrdGroupManage;

	// 查询分组
	public List<ShopGroupRspBody> qryShopGroup(String userId) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		List<ShopGroup> sgs = shopGroupManage.qryShopGroupByShopId(shopId);
		if (sgs == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商品分组");
		}
		List<HashMap> hm = shopPrdGroupManage.qryPrdGroupCntByShopId(shopId);

		List<ShopGroupRspBody> bodys = new ArrayList<>();

		for (ShopGroup sg : sgs) {
			ShopGroupRspBody body = new ShopGroupRspBody();
			body.setShopId(shopId);
			Integer groupId = sg.getId();
			body.setGroupId(groupId);
			body.setGroupName(sg.getGroupName());

			for (HashMap m : hm) {
				if (m.get("groupId") != null && (Integer) m.get("groupId") == groupId) {
					Long prdCnt = m.get("prdCnt") == null ? 0 : (Long) m.get("prdCnt");
					body.setPrdCnt(prdCnt.intValue());
					break;
				}
			}
			bodys.add(body);
		}
		return bodys;
	}

	// 创建分组
	public ShopGroupRspBody createShopGroup(String userId, ShopGroupReqBody body) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		String groupName = body.getGroupName();
		ShopGroup shopGroup = new ShopGroup();
		shopGroup.setShopId(shopId);
		shopGroup.setGroupName(groupName);
		shopGroup.setCreateTime(new Date());
		shopGroup.setCreateOper(userId);
		shopGroupManage.insert(shopGroup);
		ShopGroupRspBody rspBody = new ShopGroupRspBody();
		rspBody.setShopId(shopId);
		rspBody.setGroupId(shopGroup.getId());
		rspBody.setGroupName(groupName);
		rspBody.setPrdCnt(0);
		return rspBody;
	}

	// 创建分组内的商品
	public ProductGroupRspBody createPrdGroup(String userId, ProductGroupReqBody body) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}

		Integer shopId = ss.getShopId();
		List<String> productIds = body.getProductIds();
		Integer groupId = body.getGroupId();
		shopPrdGroupManage.deleteByGroupId(groupId);

		for (String productId : productIds) {
			ProductGroup prdGroup = new ProductGroup();
			prdGroup.setShopId(shopId);
			prdGroup.setGroupId(groupId);
			prdGroup.setProductId(productId);
			shopPrdGroupManage.insert(prdGroup);
		}

		ProductGroupRspBody rspBody = new ProductGroupRspBody();
		return rspBody;
	}

	// 删除分组
	public void deleteShopGroup(String userId, ShopGroupReqBody body) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer groupId = body.getGroupId();
		shopPrdGroupManage.deleteByGroupId(groupId);
		shopGroupManage.deleteByPrimaryKey(groupId);

	}

	// 分组重命名
	public void renameShopGroup(String userId, ShopGroupReqBody body) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		ShopGroup sg = new ShopGroup();
		sg.setId(body.getGroupId());
		sg.setGroupName(body.getGroupName());
		shopGroupManage.updateByPrimaryKeySelective(sg);

	}

	// 删除组内商品
	public void deletePrdInGroup(String userId, ShopDeleteGroupProductReq req) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Integer groupId = req.getBody().getGroupId();
		List<String> prdIds = req.getBody().getProductIds();
		if (shopId != null && groupId != null && prdIds != null && prdIds.size() > 0) {
			StringBuffer productIds = new StringBuffer();
			productIds.append("(");
			for (String id : prdIds) {
				productIds.append("'").append(id).append("',");
			}
			String ids = productIds.toString().substring(0, productIds.toString().length() - 1) + ")";

			shopPrdGroupManage.deletePrdInGroup(shopId, groupId, ids);
		}
	}

	// 移动组内商品
	public void movePrdInGroup(String userId, ShopMoveGroupProductReq req) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Integer sourceGroupId = req.getBody().getSourceGroupId();
		Integer targetGroupId = req.getBody().getTargetGroupId();
		List<String> productIds = req.getBody().getProductIds();
		if (shopId != null && sourceGroupId != null && targetGroupId != null && productIds != null
				&& productIds.size() > 0) {
			// 删除源组内商品
			StringBuffer prdIds = new StringBuffer();
			prdIds.append("(");
			for (String id : productIds) {
				prdIds.append("'").append(id).append("',");
			}
			String ids = prdIds.toString().substring(0, prdIds.toString().length() - 1) + ")";

			shopPrdGroupManage.deletePrdInGroup(shopId, sourceGroupId, ids);

			// 在目标组内添加商品

			for (String productId : productIds) {

				ProductGroup prdGroup = new ProductGroup();
				prdGroup.setShopId(shopId);
				prdGroup.setGroupId(targetGroupId);
				prdGroup.setProductId(productId);
				ProductGroup anotherPrdGroup = shopPrdGroupManage.qryByPrdIdGroupId(prdGroup);
				if (anotherPrdGroup == null) {
					shopPrdGroupManage.insertSelective(prdGroup);
				}
			}

		}
	}

}
