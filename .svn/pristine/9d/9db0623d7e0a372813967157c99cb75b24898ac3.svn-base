package com.tian.sakura.cdd.srv.web.log;

import com.tian.sakura.cdd.common.req.log.UserAmountLogReq;
import com.tian.sakura.video.service.auth.UserAmountLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pub/log")
@Api(value = "喜报相关")
public class UserAmountLogController {
    @Autowired
    private UserAmountLogService userAmountLogService;

    @ApiOperation(value = "获取喜报列表")
    @PostMapping("/getUserAmountLog")
    public Object getUserAmountLog(@RequestBody UserAmountLogReq userAmountLogReq) {
        return userAmountLogService.getUserAmountLogByNum(userAmountLogReq);
    }
}
