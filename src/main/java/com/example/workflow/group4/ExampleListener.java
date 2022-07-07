package com.example.workflow.group4;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExampleListener implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setDueDate(new Date());

    }
}
