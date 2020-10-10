package com.tian.sakura.cdd.db.manage.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.product.ProductSpecMapper;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductSpecManage extends AbstractSingleManage<ProductSpec, String> {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    protected AbstractSingleMapper<ProductSpec, String> getSingleMapper() {
        return productSpecMapper;
    }

    public List<ProductSpec> listProductSpec(ProductSpec productSpec) {
        return productSpecMapper.listProductSpec(productSpec.getId());
    }

    public List<ProductSpec> listProductSpecByProductId(String productId) {
        return productSpecMapper.listProductSpec(productId);
    }

    /**
     * 根据产品id和规格组合id查询 商品组合规格记录
     * @param productId 商品id
     * @param specValueId 组合规格 以下划线_
     * @return
     */
    public ProductSpec selectByProductIdAndSpecValueIds(String productId, String specValueId) {
        return productSpecMapper.selectByProductIdAndSpecValueId(productId, specValueId);
    }


    public void deleteProductSpec(String id) {
        productSpecMapper.updateDeleteFlag(id);
    }

    public void decreaseStock(Integer id, Integer prdCount) {
        productSpecMapper.decreaseStock(id, prdCount);
    }

    public void increaseStock(Integer id, Integer prdCount) {
        productSpecMapper.increaseStock(id, prdCount);
    }
}
