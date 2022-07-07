package com.example.workflow.mvc.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LazyWorkerEvent implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("LazyWorkerEvent:eventName"+delegateExecution.getEventName()+" "+(new Date()));

    }
}
