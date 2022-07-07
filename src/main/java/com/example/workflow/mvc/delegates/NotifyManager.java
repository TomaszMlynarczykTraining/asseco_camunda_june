package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyManager  implements JavaDelegate {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        MessageCorrelationResult mr = runtimeService.createMessageCorrelation("Message_lazyWorkers")
                .processInstanceId(delegateExecution.getProcessInstanceId())
                .correlateWithResult();
        System.out.println("ResultType: "+mr.getResultType());
    }
}
