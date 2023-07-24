package com.xkcoding.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoTaskApplicationTests {
    @Autowired
    TaskService taskService;

    @Test
    public void contextLoads() throws InterruptedException {
        Thread.sleep(50000);
        SysJobPO sysJobPO = new SysJobPO();
        sysJobPO.setBeanName("taskJob2");
        sysJobPO.setMethodName("start1");
        taskService.delete(sysJobPO);
        System.out.println("delete");
        Thread.sleep(1000000);
    }
    @Test
    public void contextLoads2() throws InterruptedException {
        Thread.sleep(50000);
        SysJobPO sysJobPO2 = new SysJobPO();
        sysJobPO2.setJobId(2);
        sysJobPO2.setBeanName("taskJob2");
        sysJobPO2.setMethodName("start");
        sysJobPO2.setCronExpression("0 */1 * * * ?");
        sysJobPO2.setJobStatus(1);
        sysJobPO2.setRemark("");
        sysJobPO2.setCreateTime(new Date());
        sysJobPO2.setUpdateTime(new Date());
        taskService.addTask(sysJobPO2);
        System.out.println("add");
        Thread.sleep(1000000);
    }


}
