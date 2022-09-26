package com.xkcoding.task.quartz.job;

import cn.hutool.core.date.DateUtil;
import com.xkcoding.task.quartz.job.base.BaseJob;
import com.xkcoding.task.quartz.mapper.JobMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <p>
 * Hello Job
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 13:22
 */
@Slf4j
@Component
public class HelloJob implements BaseJob {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(context);
        System.out.println(context.getJobDetail().getJobDataMap().get("dataType"));
        Assert.isNull(jobMapper, "mapper is null!");
        log.error("Hello Job 执行时间: {}", DateUtil.now());
    }
}
