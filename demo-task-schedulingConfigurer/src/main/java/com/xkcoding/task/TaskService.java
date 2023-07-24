package com.xkcoding.task;

import com.xkcoding.task.dao.ISysJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/7/18 上午 10:20
 */
@Service
public class TaskService {
    //@Autowired
    private ISysJobRepository sysJobRepository;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    public String addTask(SysJobPO sysJob) {
//        boolean success = sysJobRepository.addSysJob(sysJob);
//        if (!success)
//            return "fail";
//        else {
//            if (sysJob.getJobStatus().equals("1")) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
//            }
//        }
        return "ok";
    }

    public String delete(SysJobPO existedSysJob) {
       // boolean success = sysJobRepository.deleteSysJobById(req.getJobId());
//        if (!success)
//            return OperationResUtils.fail("删除失败");
//        else{
//            if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
//            }
//        }

        return "ok";
    }
}
