package com.tian.sakura.cdd.srv.web.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tian.sakura.cdd.srv.service.base.ActivityQueryService;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityRspBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/activity")
@Api(value = "首页活动区api", tags = {"活动区"})
public class ActivityController {
	@Autowired
	private ActivityQueryService activityService;
    @RequestMapping(value = "listAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("查询首页活动区")

	public List<ActivityRspBody> getAllActivity(){
		return activityService.getAllActivity();
	}
}
