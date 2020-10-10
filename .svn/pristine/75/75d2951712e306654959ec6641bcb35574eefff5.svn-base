package com.tian.sakura.cdd.srv.service.base;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.banner.Banner;
import com.tian.sakura.cdd.db.manage.banner.BannerManage;
import com.tian.sakura.cdd.srv.web.base.dto.BannerReq;
import com.tian.sakura.cdd.srv.web.base.dto.BannerRsqBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class BannerQueryService {

    @Autowired
    private BannerManage bannerManage;

    public List<BannerRsqBody> listUsableBanner(BannerReq bannerReq) {
        List<Banner> bannerList = bannerManage.listUsableBanner(bannerReq.getBody().getBannerType());

        List<BannerRsqBody> result = new ArrayList<>();
        for (Banner banner : bannerList) {

            BannerRsqBody bannerRsqBody = new BannerRsqBody();
            bannerRsqBody.setBannerName(banner.getBannerName());
            bannerRsqBody.setBannerType(banner.getBannerType());
            bannerRsqBody.setBannerUrl(banner.getBannerUrl());
            bannerRsqBody.setJumpUrl(banner.getJumpUrl());
            result.add(bannerRsqBody);
        }
        return result;
    }
}
