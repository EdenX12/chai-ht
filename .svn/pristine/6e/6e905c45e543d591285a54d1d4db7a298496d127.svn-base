package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import com.tian.sakura.cdd.db.domain.productReviewStat.ProductReviewStat;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.PrdRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.ProductSpecValueListVo;
import com.tian.sakura.cdd.srv.web.product.dto.ProductSpecValueVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class ProductBuilder {

    public static List<ProductSpecValueListVo> extractSpecValue(List<ProductSpecValue> productSpecValueList) {
        List<ProductSpecValueListVo> result = new ArrayList<>();
        Map<String, List<ProductSpecValueVo>> specValueMap = extractSpecValueToMap(productSpecValueList);

        specValueMap.entrySet().stream().forEach(entry ->{
            ProductSpecValueListVo vo = new ProductSpecValueListVo();
            vo.setSpecType(entry.getKey());
            vo.setSpecValueVoList(entry.getValue());
            result.add(vo);

        });
        return result;
    }

    public static Map<String, List<ProductSpecValueVo>> extractSpecValueToMap(List<ProductSpecValue> productSpecValueList) {
        List<ProductSpecValueListVo> result = new ArrayList<>();
        Map<String, List<ProductSpecValueVo>> specValueMap = new HashMap<>();
        for (ProductSpecValue specValue : productSpecValueList) {
            String valueType = specValue.getValueType();
            List<ProductSpecValueVo> child = null;
            if (specValueMap.containsKey(valueType)) {
                child = specValueMap.get(valueType);
            } else {
                child = new ArrayList<>();
            }
            ProductSpecValueVo currentItem = new ProductSpecValueVo();
            currentItem.setSpecValueId(specValue.getId());
            currentItem.setSpecValueType(specValue.getValueType());
            currentItem.setSpecValueName(specValue.getValueName());
            child.add(currentItem);

            specValueMap.put(valueType, child);
        }

        return specValueMap;
    }

    public static ProductReviewStat buildNewReviewStat(String productId) {

        ProductReviewStat productReviewStat = new ProductReviewStat();
        productReviewStat.setId(IdGenUtil.uuid());
        productReviewStat.setProductId(productId);
        productReviewStat.setCnt(1);
        return productReviewStat;
    }
}
