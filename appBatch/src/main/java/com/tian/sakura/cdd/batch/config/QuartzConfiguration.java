package com.tian.sakura.cdd.batch.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * quartz的配置类
 *
 * @author lvzonggang
 * @Date 2018/10/30
 */
@Configuration
public class QuartzConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private QuartzJobFactory quartzJobFactory;

    private static final String SCHEDULER_NAME = "appBatchScheduler";

    /**
     * 配置任务调度器
     * 使用项目数据源作为quartz数据源
     *
     * @return
     * @throws Exception
     */
    @Bean(destroyMethod = "destroy", autowire = Autowire.NO)
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //将spring管理job自定义工厂交由调度器维护
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        schedulerFactoryBean.setSchedulerName(SCHEDULER_NAME);
        //设置覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //项目启动完成后，等待15秒后开始执行调度器初始化
        schedulerFactoryBean.setStartupDelay(15);
        //设置调度器自动运行
        schedulerFactoryBean.setAutoStartup(true);
        //设置数据源，使用与项目统一数据源
        schedulerFactoryBean.setDataSource(dataSource);
        //设置上下文spring bean name
        //schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        //设置配置文件位置
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return schedulerFactoryBean;
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "cddBatchScheduler")
    @Primary
    public Scheduler scheduler() {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        return scheduler;
    }

}