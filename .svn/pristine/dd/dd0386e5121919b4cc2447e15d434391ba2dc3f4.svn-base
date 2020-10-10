package com.tian.sakura.cdd.srv.service.product;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import com.tian.sakura.cdd.db.manage.product.ProductSpecValueManage;
import com.tian.sakura.cdd.srv.builder.ProductBuilder;
import com.tian.sakura.cdd.srv.web.product.dto.ProductReq;
import com.tian.sakura.cdd.srv.web.product.dto.ProductReqBody;
import com.tian.sakura.cdd.srv.web.product.dto.ProductSpecValueListVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class ProductQueryServcieTest {

    @Autowired
    private ProductQueryService productQueryService;

    @Test
    public void queryProductList() {
        ProductReq req = new ProductReq();
        ProductReqBody body = new ProductReqBody();
        body.setRecommendTypeId("1");
        req.setBody(body);

        System.out.println(JSON.toJSONString(productQueryService.getProductList(req)));
    }

    @Test
    public void detail() {
        String productId = "ad16a053bf6e4008ae6926149a207d5f";

        System.out.println(JSON.toJSONString(productQueryService.getProductDetail(productId)));
    }

    @Test
    public void queryRecommendPrd() {
        ProductReq req = new ProductReq();
        //req.setHead();

        ProductReqBody body = new ProductReqBody();
        body.setRecommendTypeId("1");
        req.setBody(body);
        //System.out.println(JSON.toJSONString(productQueryService.getHotPrdList(req)));

        body.setRecommendTypeId("2");
        //req.setBody(body);
        System.out.println(JSON.toJSONString(productQueryService.getDiscountPrdList(req)));
    }

    @Autowired
    private ProductSpecValueManage productSpecValueManage;
    @Test
    public void xx() {
        List<ProductSpecValue> specValueForUseList = productSpecValueManage.selectByProductTypeId("7");
        List<ProductSpecValueListVo> specValueListVoList = ProductBuilder.extractSpecValue(specValueForUseList);
        System.out.println(JSON.toJSONString(specValueListVoList));
    }
}
