package com.tian.sakura.cdd.db.manage.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.activity.ActivityMapper;
import com.tian.sakura.cdd.db.domain.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityManage extends AbstractSingleManage<Activity, String> {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    protected AbstractSingleMapper<Activity, String> getSingleMapper() {
        return activityMapper;
    }

    public List<Activity> getAllActivity() {
        return activityMapper.getAllActivity();
    }

    public List<Activity> listActivity(Activity activity) {
    	return activityMapper.listActivity(activity);
    }

	public void deleteActivity(String id) {
    	activityMapper.deleteActivity(id);
	}
}
