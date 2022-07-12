package com.example.workflow.group4;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class Group4SendMessageDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngine().getRuntimeService().createMessageCorrelation("Message_zacznij_alternatywny_proces")
                .processInstanceBusinessKey("KEY0123")
                .correlateWithResult();
    }
}
