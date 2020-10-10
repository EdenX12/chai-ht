package com.tian.sakura.cdd.db.manage.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.product.AdminProductTypeReq;
import com.tian.sakura.cdd.common.req.product.ProductTypeReqBody;
import com.tian.sakura.cdd.db.dao.productType.ProductTypeMapper;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTypeManage extends AbstractSingleManage<ProductType, Integer> {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    protected AbstractSingleMapper<ProductType, Integer> getSingleMapper() {
        return productTypeMapper;
    }

    public List<ProductType> findOneLevelType() {
        Map<String,Object> param = new HashMap<>();
        param.put("level", 1);
        return productTypeMapper.getProductTypeByCondition(param);
    }

    public List<ProductType> findTwoLevelType(String parentId) {
        Map<String,Object> param = new HashMap<>();
        param.put("parentId", parentId);
        return productTypeMapper.getProductTypeByCondition(param);
    }

    public List<ProductType> findByTypeStatus(ProductTypeReqBody productTypeReqBody) {
        return productTypeMapper.findByTypeStatus(productTypeReqBody);
    }

    public List<ProductType> getAllProductType() {
        Map<String,Object> param = new HashMap<>();
        return productTypeMapper.getProductTypeByCondition(param);
    }

    public List<ProductType> listProductType(AdminProductTypeReq req) {
        return productTypeMapper.listProductType(req);
    }

    public List<ProductType> listProductTypeChild(AdminProductTypeReq productType) {
        return productTypeMapper.listProductTypeChild(productType);
    }

    public List<ProductType> queryProductTypeByParentId(Integer value) {
        return productTypeMapper.queryProductTypeByParentId(value);
    }
}
