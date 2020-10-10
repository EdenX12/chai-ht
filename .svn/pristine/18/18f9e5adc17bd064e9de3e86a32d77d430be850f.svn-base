package com.tian.sakura.cdd.db.manage.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.product.ProductSpecValueMapper;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class ProductSpecValueManage extends AbstractSingleManage<ProductSpecValue, String> {

    @Autowired
    private ProductSpecValueMapper productSpecValueMapper;

    @Override
    protected AbstractSingleMapper<ProductSpecValue, String> getSingleMapper() {
        return productSpecValueMapper;
    }

    // 根据产品二级分类查询
    public List<ProductSpecValue> selectByProductTypeId(String productTypeId) {
        return productSpecValueMapper.selectByProductTypeId(productTypeId);
    }
}
