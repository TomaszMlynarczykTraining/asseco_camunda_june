package com.example.workflow.mvc;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.stereotype.Component;

@Component
public class TaskListener implements org.camunda.bpm.engine.delegate.TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("Task listener");

    }
}
