package com.tian.sakura.cdd.batch.schedule;

import com.tian.sakura.cdd.batch.service.PrdOrderCloseService;
import com.tian.sakura.cdd.batch.service.TaskOrderCloseService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrdOrderCheckOverdueScheduleJobs extends BaseScheduleJobs implements Job, InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PrdOrderCloseService prdOrderCloseService;

	@Override
	public void afterPropertiesSet() throws Exception {
		String jobName = "PrdOrderCheckOverdueScheduleJob";
		String desc = "商品订单超期未支付的定时任务";
		String triggerName = "PrdOrderCheckOverdueScheduleTrigger";
		String cronExpress = "0 0/5 * * * ?";

		// 初始化并启动任务
		initAndScheduleJob(this.getClass(),jobName, desc, triggerName, cronExpress);
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("商品订单超期未支付任务开始===start");
		prdOrderCloseService.closeOrder();
		logger.info("商品订单超期未支付任务结束===end");
		
	}

}
