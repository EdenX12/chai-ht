package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.order.UserShopCar;
import com.tian.sakura.cdd.srv.web.order.dto.shopcar.ShopCarAddReqBody;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class ShopCarBuilder {

    public static UserShopCar build(ShopCarAddReqBody body) {
        UserShopCar userShopCar = new UserShopCar();
        userShopCar.setId(IdGenUtil.uuid());
        userShopCar.setProductId(body.getProductId());
        // 移到service赋值
        //userShopCar.setProductSpecId(body.getProductSpecId());

        userShopCar.setCount(body.getCount());
        return userShopCar;
    }
}
