package com.tian.sakura.cdd.srv.service.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.srv.web.product.dto.ProductReq;
import com.tian.sakura.cdd.srv.web.product.dto.ProductReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ProductTypeTest {
    @Autowired
    private ProductQueryService productQueryService;

    @Test
    public void queryProductList() {
        System.out.println(JSON.toJSONString(productQueryService.getAllProductType()));
    }

}
