package com.tian.sakura.cdd.batch.schedule;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Component
public class DemoScheduleJobs implements Job, InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Scheduler scheduler;

	@Override
	public void afterPropertiesSet() throws Exception {
		String jobName = "demoScheduleJob";
		String jobGroupName = "group";
		JobKey jobkey = JobKey.jobKey(jobName, jobGroupName);
		JobDetail jobDetail = JobBuilder.newJob(this.getClass())
				.withDescription("测试批量任务案例")
				.withIdentity(jobkey)
				.build();
		String triggerName = "serviceItemScheduleTrigger";
		String triggerGroup = "tr-group";
		String cronExpress = "0/30 * * * * ?";
		Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(triggerName, triggerGroup)
				.withSchedule(cronSchedule(cronExpress))
				.build();
		if (!scheduler.checkExists(jobkey)) {
			scheduler.scheduleJob(jobDetail, trigger);
		}
		
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//logger.info("demo演示任务开始===start");

		//logger.info("demo演示任务结束===end");
		
	}

}
