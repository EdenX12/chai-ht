package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.cdd.db.domain.banner.Banner;
import com.tian.sakura.cdd.srv.service.base.BannerQueryService;
import com.tian.sakura.cdd.srv.web.base.dto.BannerReq;
import com.tian.sakura.cdd.srv.web.base.dto.BannerRsqBody;
import com.tian.sakura.video.service.auth.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/banner")
@Api(value = "banner模块" , tags = {"banner"})
public class BannerController {

    @Autowired
    private BannerQueryService bannerQueryService;

    @ApiOperation(value = "获取banner列表")
    @PostMapping("/list")
    public List<BannerRsqBody> listBanner(@RequestBody @Valid BannerReq bannerReq) {
        return bannerQueryService.listUsableBanner(bannerReq);
    }
}
