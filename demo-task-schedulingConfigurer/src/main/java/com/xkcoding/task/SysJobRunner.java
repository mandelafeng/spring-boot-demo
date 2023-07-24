package com.xkcoding.task;

import com.xkcoding.task.dao.ISysJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

    //@Autowired
    private ISysJobRepository sysJobRepository;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        List<SysJobPO> jobList = new ArrayList<>();
        SysJobPO sysJobPO = new SysJobPO();
        sysJobPO.setJobId(1);
        sysJobPO.setBeanName("taskJob2");
        sysJobPO.setMethodName("start1");
        sysJobPO.setCronExpression("0 */1 * * * ?");
        sysJobPO.setJobStatus(1);
        sysJobPO.setRemark("");
        sysJobPO.setCreateTime(new Date());
        sysJobPO.setUpdateTime(new Date());

        SysJobPO sysJobPO2 = new SysJobPO();
        sysJobPO2.setJobId(2);
        sysJobPO2.setBeanName("taskJob2");
        sysJobPO2.setMethodName("start2");
        sysJobPO2.setCronExpression("0 */1 * * * ?");
        sysJobPO2.setJobStatus(1);
        sysJobPO2.setRemark("");
        sysJobPO2.setCreateTime(new Date());
        sysJobPO2.setUpdateTime(new Date());

        jobList.add(sysJobPO);
        jobList.add(sysJobPO2);
        // List<SysJobPO> jobList = sysJobRepository.getSysJobListByStatus(SysJobStatus.NORMAL.ordinal());
        if (jobList != null && jobList.size() > 0) {
            for (SysJobPO job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }
            logger.info("定时任务已加载完毕...");
        }
    }
}
