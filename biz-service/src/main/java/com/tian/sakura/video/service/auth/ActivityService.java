package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.activity.AdminActivityReq;
import com.tian.sakura.cdd.common.resp.activity.AdminActivityProductResp;
import com.tian.sakura.cdd.db.domain.activity.Activity;
import com.tian.sakura.cdd.db.domain.activity.ActivityProduct;
import com.tian.sakura.cdd.db.manage.activity.ActivityProductManage;
import com.tian.sakura.cdd.db.manage.base.ActivityManage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityManage activityManage;
    @Autowired
    private ActivityProductManage activityProductManage;

    public PageInfo<Activity> listActivity(AdminActivityReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        Activity activity = new Activity();
        BeanUtils.copyProperties(req, activity);
        return new PageInfo<>(activityManage.listActivity(activity));
    }

    public void insertActivity(Activity activity) {
        activityManage.insertSelective(activity);
    }

    public void updateActivity(Activity activity) {
        activityManage.updateByPrimaryKeySelective(activity);
    }

    public void deleteActivity(Activity activity) {
        activityManage.deleteActivity(activity.getId());
    }

    public PageInfo<AdminActivityProductResp> listActivityProduct(AdminActivityReq activityReq) {
        PageHelper.startPage(activityReq.getPageNum(), activityReq.getPageSize());
        return new PageInfo<>(activityProductManage.listActivityProduct(activityReq));
    }

    public void insertActivityProduct(ActivityProduct activityProduct) {
        activityProductManage.insertSelective(activityProduct);
    }

    public void updateActivityProduct(ActivityProduct activityProduct) {
        activityProductManage.updateByPrimaryKeySelective(activityProduct);
    }

    public void deleteActivityProduct(ActivityProduct activityProduct) {
        activityProductManage.deleteByPrimaryKey(activityProduct.getId());
    }

    public void insertActivityProductList(ActivityProduct activityProduct) {
        activityProduct.getProductList().forEach(e -> {
            ActivityProduct a = new ActivityProduct();
            a.setSOrder(1);
            a.setProductId(e.getId());
            a.setActId(activityProduct.getActId());
            if (activityProductManage.selectByActIdAndProductId(activityProduct.getActId(), e.getId()) <= 0) {
                activityProductManage.insertSelective(a);
            }
        });
    }
}
