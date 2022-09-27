package com.xkcoding.task.quartz.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义Job工厂类
 * <p>
 * job的初始化时quartz在执行时new出来的。不受spring的管理，无法注入相关的依赖bean
 * 所以需要在job在被创建的时候，使用AutowireCapableBeanFactory实现自动转配Job实例
 * <p>
 * AutowireCapableBeanFactory，可以利用这个接口自动装配那些不在spring控制范围内的bean实例
 */

@Component
public class MyJobFactory extends AdaptableJobFactory {

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        // 自动装配实例
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return super.createJobInstance(bundle);
    }

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;


}
