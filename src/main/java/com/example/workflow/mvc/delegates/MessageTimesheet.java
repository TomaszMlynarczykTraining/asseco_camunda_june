package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.processes.TimesheetProcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageTimesheet implements JavaDelegate {
    private final RuntimeService ruService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Map<String, Object> vars = new HashMap<>();
        vars.put(TimesheetProcess.OVER_HOURS_FORM_ID, "someId");
        MessageCorrelationResult res = ruService
                .createMessageCorrelation("messageTimesheet")
               // .processInstanceId(delegateExecution.getProcessInstanceId())
                .setVariables(vars)
                .correlateWithResult();

        log.info("Wynik korelacji:{}",res.getResultType());
    }
}
