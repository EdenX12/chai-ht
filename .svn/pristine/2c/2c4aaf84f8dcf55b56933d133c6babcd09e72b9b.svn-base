package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.activity.AdminActivityReq;
import com.tian.sakura.cdd.db.domain.activity.Activity;
import com.tian.sakura.cdd.db.domain.activity.ActivityProduct;
import com.tian.sakura.video.service.auth.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/listActivity")
    public ResultDto listActivity(@RequestBody AdminActivityReq req) {
        return ResultDto.success().setObj(activityService.listActivity(req));
    }

    @PostMapping("/insertActivity")
    public ResultDto insertActivity(@RequestBody Activity activity) {
        activityService.insertActivity(activity);
        return ResultDto.success();
    }

    @PostMapping("/updateActivity")
    public ResultDto updateActivity(@RequestBody Activity activity) {
        activityService.updateActivity(activity);
        return ResultDto.success();
    }

    @PostMapping("/deleteActivity")
    public ResultDto deleteActivity(@RequestBody Activity activity) {
        activityService.deleteActivity(activity);
        return ResultDto.success();
    }

    @PostMapping("/listActivityProduct")
    public ResultDto listActivityProduct(@RequestBody AdminActivityReq activityReq) {
        return ResultDto.success().setObj(activityService.listActivityProduct(activityReq));
    }

    @PostMapping("/insertActivityProduct")
    public ResultDto insertActivityProduct(@RequestBody ActivityProduct activityProduct) {
        activityService.insertActivityProduct(activityProduct);
        return ResultDto.success();
    }

    @PostMapping("/insertActivityProductList")
    public ResultDto insertActivityProductList(@RequestBody ActivityProduct activityProduct) {
        activityService.insertActivityProductList(activityProduct);
        return ResultDto.success();
    }

    @PostMapping("/updateActivityProduct")
    public ResultDto updateActivityProduct(@RequestBody ActivityProduct activityProduct) {
        activityService.updateActivityProduct(activityProduct);
        return ResultDto.success();
    }

    @PostMapping("/deleteActivityProduct")
    public ResultDto deleteActivityProduct(@RequestBody ActivityProduct activityProduct) {
        activityService.deleteActivityProduct(activityProduct);
        return ResultDto.success();
    }
}
