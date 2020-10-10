package com.tian.sakura.cdd.db.manage.activity;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.activity.AdminActivityReq;
import com.tian.sakura.cdd.common.resp.activity.AdminActivityProductResp;
import com.tian.sakura.cdd.db.dao.activity.ActivityProductMapper;
import com.tian.sakura.cdd.db.domain.activity.ActivityProduct;
import com.tian.sakura.cdd.db.manage.activity.vo.ActivityProductQueryVo;
import com.tian.sakura.cdd.db.manage.activity.vo.ActivityProductVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityProductManage extends AbstractSingleManage<ActivityProduct, String> {

    @Autowired
    private ActivityProductMapper activityProductMapper;

    @Override
    protected AbstractSingleMapper<ActivityProduct, String> getSingleMapper() {
        return activityProductMapper;
    }

    public List<AdminActivityProductResp> listActivityProduct(AdminActivityReq activityReq) {
        return activityProductMapper.listActivityProduct(activityReq);
    }
    
    //根据活动id获取商品
    public List<ActivityProductVo> getProductsByActivityId(ActivityProductQueryVo queryVo){
    	return activityProductMapper.getProductsByActivityId(queryVo);
    }

    public int selectByActIdAndProductId(String actId, String id) {
        return activityProductMapper.selectByActIdAndProductId(actId,id);
    }
}
