package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopCatlog.ShopCatlog;
import com.tian.sakura.video.service.auth.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/listShop")
    public ResultDto listShop(@RequestBody ShopReq shopReq) {
        return ResultDto.success().setObj(shopService.listShop(shopReq));
    }

    @PostMapping("/insertShop")
    public ResultDto insertShop(@RequestBody Shop shop) {
        shopService.insertShop(shop);
        return ResultDto.success();
    }

    @PostMapping("/updateShop")
    public ResultDto updateShop(@RequestBody Shop shop) {
        shopService.updateShop(shop);
        return ResultDto.success();
    }

    @PostMapping("/deleteShop")
    public ResultDto deleteShop(@RequestBody Shop shop) {
        shopService.deleteShop(shop);
        return ResultDto.success();
    }

    @PostMapping("/listCatRel")
    public ResultDto listCatRel(@RequestBody ShopCatlog shopCatlog) {
        return ResultDto.success().setObj(shopService.listCatRel(shopCatlog));
    }

    @PostMapping("/listGroup")
    public ResultDto listGroup(@RequestBody ShopCatlog shopCatlog) {
        return ResultDto.success().setObj(shopService.listGroup(shopCatlog));
    }
}
