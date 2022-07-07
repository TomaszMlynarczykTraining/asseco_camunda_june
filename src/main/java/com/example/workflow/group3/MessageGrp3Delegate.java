package com.example.workflow.group3;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageGrp3Delegate implements JavaDelegate {
    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        runtimeService.createMessageCorrelation(Group3ProcessVariables.MSG_GRP3)
        .processInstanceBusinessKey("AGXX").correlate();
      //  for(MessageCorrelationResult temp:result){
      //      System.out.println(temp.getResultType().toString());
      //  }

    }

}
