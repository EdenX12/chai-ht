package com.tian.sakura.cdd.srv.web.order;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.order.ShopCarApiService;
import com.tian.sakura.cdd.srv.web.order.dto.shopcar.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Api("购物车api")
@RestController
@RequestMapping("shopCar")
public class ShopCarController {

    @Autowired
    private ShopCarApiService shopCarApiService;

    @ApiOperation("查询购物车商品")
    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public List<ShopCarQueryRsqBodyNoPage> list(@RequestBody @Valid ShopCarQueryReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        return shopCarApiService.listNoPage(user.getId());
    }


    @ApiOperation("添加购物车商品")
    @PostMapping("add")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void addShopCar(@RequestBody @Valid ShopCarAddReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        shopCarApiService.addShopCar(user, req);
    }

    @ApiOperation("编辑购物车商品数量")
    @PostMapping("editCount")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void editShopCar(@RequestBody @Valid ShopCarEditReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        shopCarApiService.editShopCar(user, req);
    }


    @ApiOperation("删除购物车商品")
    @PostMapping("batchDelete")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void batchDelete(@RequestBody @Valid ShopCarDeleteReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        List<String> ids = req.getBody().getIds();
        shopCarApiService.batchDelete(user, ids);
    }

    @ApiOperation("查询总数量")
    @PostMapping("findCount")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public int findCount() {
        SUser user = LoginUserThreadLocal.getLoginUser();

        return shopCarApiService.findCount(user);
    }
}
