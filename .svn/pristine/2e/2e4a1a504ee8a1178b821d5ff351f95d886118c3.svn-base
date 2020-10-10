package com.tian.sakura.cdd.db.dao.productImg;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.productImg.ProductImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductImgMapper  extends AbstractSingleMapper<ProductImg,Integer>{
    //根据产品id查询图片
	public List<ProductImg> qryPrdImg(String productId);

    List<ProductImg> queryProductImg(@Param("id") String id);

    void deleteByProduct(@Param("productId") String productId);
}