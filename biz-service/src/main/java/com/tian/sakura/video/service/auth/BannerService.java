package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.banner.AdminBannerReq;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.banner.Banner;
import com.tian.sakura.cdd.db.manage.banner.BannerManage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {
    @Autowired
    private BannerManage bannerManage;

    public PageInfo<Banner> listBanner(AdminBannerReq adminBannerReq) {
        PageHelper.startPage(adminBannerReq.getPageNum(), adminBannerReq.getPageSize());
        Banner banner = new Banner();
        BeanUtils.copyProperties(adminBannerReq, banner);
        return new PageInfo<>(bannerManage.listBanner(banner));
    }

    public void insertBanner(Banner banner) {
        bannerManage.insertSelective(banner);
    }

    public void updateBanner(Banner banner) {
        bannerManage.updateByPrimaryKeySelective(banner);
    }

    public void deleteBanner(Banner banner) {
        bannerManage.deleteBanner(banner.getId());
    }
}
