package com.tian.sakura.cdd.db.dao.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.product.RecommendTypeReq;
import com.tian.sakura.cdd.db.domain.base.RecommendType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendTypeMapper extends AbstractSingleMapper<RecommendType, String> {

    List<RecommendType> listRecommendType();

    List<RecommendType> listRecommendTypePage(RecommendTypeReq req);

    int deleteRecommendType(@Param("id") String id);
}