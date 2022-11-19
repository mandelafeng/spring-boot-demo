package com.hcf.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class ManagerTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Manager 审批");
        delegateTask.setAssignee("经理");
    }

}
