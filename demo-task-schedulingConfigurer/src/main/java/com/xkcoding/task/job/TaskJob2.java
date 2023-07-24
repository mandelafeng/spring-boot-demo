package com.xkcoding.task.job;

import org.springframework.stereotype.Component;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/7/18 上午 10:36
 */
@Component
public class TaskJob2 {
    public void start1() {
        System.out.println("任务启动 start1 执行了......");
    }
    public void start2() {
        System.out.println("任务启动 start2 执行了......");
    }
    public void start() {
        System.out.println("动态 添加 执行了......");
    }
}
