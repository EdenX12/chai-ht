package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.common.req.product.CategoryReq;
import com.tian.sakura.cdd.common.resp.product.CategoryResp;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import com.tian.sakura.cdd.db.manage.product.ProductTypeManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeManage productTypeManage;

    public List<CategoryResp> listCategory(CategoryReq categoryReq) {
        List<ProductType> productTypes = productTypeManage.queryProductTypeByParentId(categoryReq.getValue());
        List<CategoryResp> categoryRespList = new ArrayList<>();
        Boolean leaf = false;
        if (categoryReq.getValue() != null) {
            leaf = true;
        }
        for (ProductType e : productTypes) {
            CategoryResp categoryResp = CategoryResp.builder()
                    .value(e.getId())
                    .label(e.getTypeName())
                    .leaf(leaf)
                    .build();
            categoryRespList.add(categoryResp);
        }
        return categoryRespList;
    }
}
