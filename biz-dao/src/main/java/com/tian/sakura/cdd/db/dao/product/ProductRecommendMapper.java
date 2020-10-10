package com.tian.sakura.cdd.db.dao.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.product.RecommendProductReq;
import com.tian.sakura.cdd.common.resp.product.RecommendProductResp;
import com.tian.sakura.cdd.db.domain.base.RecommendType;
import com.tian.sakura.cdd.db.domain.product.ProductRecommend;
import com.tian.sakura.cdd.db.manage.product.vo.ProductRecommendQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRecommendMapper extends AbstractSingleMapper<ProductRecommend, String> {

    List<RecommendType> selectRecommendPrd(ProductRecommendQueryVo queryVo);

    List<RecommendProductResp> listRecommend(RecommendProductReq req);

    int selectCountByProductId(@Param("productId")String productId,@Param("recommendTypeId") String recommendTypeId);
}