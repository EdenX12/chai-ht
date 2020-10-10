package com.tian.sakura.cdd.db.dao.productType;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.product.AdminProductTypeReq;
import com.tian.sakura.cdd.common.req.product.ProductTypeReqBody;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductTypeMapper extends AbstractSingleMapper<ProductType, Integer> {

    List<ProductType> findByTypeStatus(@Param("productTypeReqBody") ProductTypeReqBody productTypeReqBody);

    List<ProductType> getProductTypeByCondition(Map<String,Object> param);

    List<ProductType> listProductType(AdminProductTypeReq productType);

    List<ProductType> listProductTypeChild(AdminProductTypeReq productType);

    List<ProductType> queryProductTypeByParentId(@Param("parentId") Integer parentId);
}