package com.example.workflow.mvc;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.hibernate.engine.spi.ExecutableList;
import org.springframework.stereotype.Component;

@Component
public class AcitivtyListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

        System.out.println("Exectuion listener");
    }
}
