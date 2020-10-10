package com.tian.sakura.cdd.srv.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.db.domain.activity.Activity;
import com.tian.sakura.cdd.db.manage.base.ActivityManage;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityRspBody;

@Service
public class ActivityQueryService {
	@Autowired
	private ActivityManage activityManage;
	
	public List<ActivityRspBody> getAllActivity(){
		List<Activity> activityList =  activityManage.getAllActivity();
		return toActivityRspBody(activityList);
	}
	
	private List<ActivityRspBody> toActivityRspBody(List<Activity> acitvityList) {
		List<ActivityRspBody>  result = new ArrayList<>();
		int i =1;
		for(Activity activity:acitvityList) {
			ActivityRspBody body = new ActivityRspBody();
			body.setActId(activity.getId());
			body.setActName(activity.getActName());
			body.setFlag(activity.getFlag().toString());
			body.setPictureUrl(activity.getPictureUrl());
			body.setShowOrder(activity.getsOrder());

			result.add(body);
		}
		return result;
	}
}
