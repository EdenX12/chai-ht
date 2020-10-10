package com.tian.sakura.cdd.srv.service.order;

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.web.order.dto.shopcar.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopCarApiServiceTest {
    private SUser user;

    @Autowired
    private ShopCarApiService shopCarApiService;

    @Before
    public void init() {
        user = new SUser();
        user.setId("82f77360303a4d8bb9fad222609e2258");
        user.setUserName("15900955395");
    }

    @Test
    public void list() {
        ShopCarQueryReqBody body =  new ShopCarQueryReqBody();
        body.setPageNum(1);
        body.setPageSize(10);
        shopCarApiService.list(user.getId(), body);
    }

    @Test
    public void add() {
        ShopCarAddReq req = new ShopCarAddReq();
        ShopCarAddReqBody body = new ShopCarAddReqBody();
        body.setProductId("3ecf9e7b3c8046e7921e86d4a02b1c4a");
        body.setProductSpecId("19");
        body.setCount(1);
        req.setBody(body);
        shopCarApiService.addShopCar(user, req);
    }

    @Test
    public void edit() {
        ShopCarEditReq req = new ShopCarEditReq();

        ShopCarEditReqBody body = new ShopCarEditReqBody();
        List<ShopCarEditBody> shopCarEditBodyList = new ArrayList<>();
        ShopCarEditBody shopCar = new ShopCarEditBody();
        shopCar.setShopCarId("bd7f767f2db9450bb7a847767561aa4d");
        shopCar.setCheckStatus(1);
        shopCar.setCount(2);
        shopCarEditBodyList.add(shopCar);

        body.setShopCarList(shopCarEditBodyList);

        req.setBody(body);

        shopCarApiService.editShopCar(user, req);
    }

    @Test
    public void delete() {

        List<String> ids = new ArrayList<>();
        ids.add("faed7751c89c4307a4489ec0ab019161");
        //ids.add("11111");

        shopCarApiService.batchDelete(user, ids);
    }
}
