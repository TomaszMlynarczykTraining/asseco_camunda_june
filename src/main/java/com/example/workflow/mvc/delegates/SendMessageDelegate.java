package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("SomeMessage")
                .correlate();

    }
}
