package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class IncidentServiceTaskV2 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


    }
}
