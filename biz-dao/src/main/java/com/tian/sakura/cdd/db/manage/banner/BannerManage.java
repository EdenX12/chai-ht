package com.tian.sakura.cdd.db.manage.banner;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.banner.BannerMapper;
import com.tian.sakura.cdd.db.domain.banner.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerManage extends AbstractSingleManage<Banner, String> {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    protected AbstractSingleMapper<Banner, String> getSingleMapper() {
        return bannerMapper;
    }

    public List<Banner> listUsableBanner(Integer bannerType) {
        return bannerMapper.listUsableBanner(bannerType);
    }

    public List<Banner> listBanner(Banner banner) {
        return bannerMapper.listBanner(banner);
    }

    public void deleteBanner(String id) {
        bannerMapper.deleteBanner(id);
    }
}
