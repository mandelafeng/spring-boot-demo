package com.cfhui.bean_life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/12/8 下午 2:05
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User){
            System.out.println("User-BeanPostProcessor-初始化前增强");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User){
            System.out.println("User-BeanPostProcessor-初始化后增强");
        }
        return bean;
    }
}
