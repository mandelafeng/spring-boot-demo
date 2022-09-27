package com.xkcoding.task.quartz.config;

import com.xkcoding.task.quartz.factory.MyJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

//@Configuration
public class SchedulerConfig {

    private MyJobFactory myJobFactory;

    @Autowired
    public SchedulerConfig(MyJobFactory myJobFactory) {
        this.myJobFactory = myJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        return schedulerFactoryBean;
    }
}
