package com.tian.sakura.cdd.batch.schedule;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * 定时任务基类
 *
 * @author lvzonggang
 */
public class BaseScheduleJobs {
    protected static final String DEFAULT_GROUP_NAME = "jobGroup";
    protected static final String DEFAULT_TRIGGER_GROUP = "triggerGroup";

    @Autowired
    private Scheduler scheduler;

    protected void initAndScheduleJob(Class clz, String jobName,String jobDesc,
                                      String triggerName,String cronExpress) throws Exception {
        JobKey jobkey = JobKey.jobKey(jobName, DEFAULT_GROUP_NAME);

        JobDetail jobDetail = JobBuilder.newJob(clz)
                .withDescription("测试批量任务案例")
                .withIdentity(jobkey)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(triggerName, DEFAULT_TRIGGER_GROUP)
                .withSchedule(cronSchedule(cronExpress))
                .build();
        if (!getScheduler().checkExists(jobkey)) {
            getScheduler().scheduleJob(jobDetail, trigger);
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
