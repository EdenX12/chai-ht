package com.tian.sakura.cdd.db.manage.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.product.RecommendTypeReq;
import com.tian.sakura.cdd.db.dao.base.RecommendTypeMapper;
import com.tian.sakura.cdd.db.domain.base.RecommendType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 推荐类型 db访问类
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class RecommendTypeManage extends AbstractSingleManage<RecommendType, String> {

    @Autowired
    private RecommendTypeMapper recommendTypeMapper;

    @Override
    protected AbstractSingleMapper<RecommendType, String> getSingleMapper() {
        return recommendTypeMapper;
    }

    public List<RecommendType> listRecommendType() {
        return recommendTypeMapper.listRecommendType();
    }

    public List<RecommendType> listRecommendTypePage(RecommendTypeReq req) {
        return recommendTypeMapper.listRecommendTypePage(req);
    }

    public void deleteRecommendType(String id) {
        recommendTypeMapper.deleteRecommendType(id);
    }
}
