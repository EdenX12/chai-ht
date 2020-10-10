package com.tian.sakura.cdd.srv.applistener.listener;

import com.tian.sakura.cdd.srv.applistener.event.TaskOrderPayEvent;
import com.tian.sakura.cdd.srv.service.pay.TaskPayCallBackContext;
import com.tian.sakura.cdd.srv.service.task.ShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class TaskOrderPayShareRelationListener implements ApplicationListener<TaskOrderPayEvent> {
    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShareService shareService;

    @Override
    @Async("asyncEventExecutor")
    public void onApplicationEvent(TaskOrderPayEvent event) {
        TaskPayCallBackContext context = (TaskPayCallBackContext) event.getSource();
        String userId = context.getUserId();
        shareService.upgradeRelation(userId);

    }
}