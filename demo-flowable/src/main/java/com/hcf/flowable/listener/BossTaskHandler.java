package com.hcf.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
/***
 * @description bpmn中设置了监听器，所以需要这个类
 * @author cfhui
 * @date 2022/11/18 11:22
 */
public class BossTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Boss 审批");
        delegateTask.setAssignee("老板");
    }

}
