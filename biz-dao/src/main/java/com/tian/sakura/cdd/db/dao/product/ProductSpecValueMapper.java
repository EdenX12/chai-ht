package com.tian.sakura.cdd.db.dao.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecValueMapper extends AbstractSingleMapper<ProductSpecValue, String> {

    List<ProductSpecValue> selectByProductTypeId(String productTypeId);

    List<ProductSpecValue> listProductSpecValue(ProductSpecValue productSpecValue);

    List<ProductSpecValue> selectByIds(@Param("ids") List<String> ids);
}