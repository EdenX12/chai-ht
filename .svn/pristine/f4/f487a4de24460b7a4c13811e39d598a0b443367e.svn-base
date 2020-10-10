package com.tian.sakura.cdd.srv.service.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.db.domain.productType.ProductType;
import com.tian.sakura.cdd.db.manage.product.ProductTypeManage;
import com.tian.sakura.cdd.srv.web.shop.dto.CatalogRspBody;

/**
 * 经营类目字典
 * @author liuhg
 *
 */
@Service
public class CatalogService {

	@Autowired
	private ProductTypeManage prdType ;
	//查询经营类目字典
	public List<CatalogRspBody> qryShopCatInfo(){
		List<ProductType> prdTypes = prdType.findOneLevelType();
		List<CatalogRspBody> rsps = new ArrayList<>();
		for(ProductType p:prdTypes) {
			CatalogRspBody rsp = new CatalogRspBody();
			rsp.setCatlogId(p.getId());
			rsp.setCatalogName(p.getTypeName());
			rsps.add(rsp);
		}
		return rsps;
	}
}
