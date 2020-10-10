package com.tian.sakura.cdd.db.manage.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.productImg.ProductImgMapper;
import com.tian.sakura.cdd.db.domain.productImg.ProductImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品图片
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class ProductImgManage extends AbstractSingleManage<ProductImg, Integer> {

    @Autowired
    private ProductImgMapper productImgMapper;

    @Override
    protected AbstractSingleMapper<ProductImg, Integer> getSingleMapper() {
        return productImgMapper;
    }

    public List<ProductImg> selectByProductId(String productId) {
        return productImgMapper.qryPrdImg(productId);
    }
}
