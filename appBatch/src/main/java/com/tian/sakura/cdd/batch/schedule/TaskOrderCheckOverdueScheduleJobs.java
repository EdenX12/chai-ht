package com.tian.sakura.cdd.batch.schedule;

import com.tian.sakura.cdd.batch.service.TaskOrderCloseService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Component
public class TaskOrderCheckOverdueScheduleJobs extends BaseScheduleJobs implements Job, InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskOrderCloseService taskOrderCloseService;

	@Override
	public void afterPropertiesSet() throws Exception {
		String jobName = "TaskOrderCheckOverdueScheduleJob";
		String desc = "任务金订单超期未支付的定时任务";
		String triggerName = "TaskOrderCheckOverdueScheduleTrigger";
		String cronExpress = "0 0/5 * * * ?";

		// 初始化并启动任务
		initAndScheduleJob(this.getClass(),jobName, desc, triggerName, cronExpress);
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("任务金订单超期未支付任务开始===start");
		taskOrderCloseService.closeTaskOrder();
		logger.info("任务金订单超期未支付任务结束===end");
		
	}

}
