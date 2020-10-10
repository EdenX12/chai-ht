package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.banner.AdminBannerReq;
import com.tian.sakura.cdd.db.domain.banner.Banner;
import com.tian.sakura.video.service.auth.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("/listBanner")
    public ResultDto listBanner(@RequestBody AdminBannerReq banner) {
        return ResultDto.success().setObj(bannerService.listBanner(banner));
    }

    @PostMapping("/insertBanner")
    public ResultDto insertBanner(@RequestBody Banner banner) {
        bannerService.insertBanner(banner);
        return ResultDto.success();
    }

    @PostMapping("/updateBanner")
    public ResultDto updateBanner(@RequestBody Banner banner) {
        banner.setUpdateTime(new Date());
        bannerService.updateBanner(banner);
        return ResultDto.success();
    }

    @PostMapping("/deleteBanner")
    public ResultDto deleteBanner(@RequestBody Banner banner) {
        bannerService.deleteBanner(banner);
        return ResultDto.success();
    }
}
