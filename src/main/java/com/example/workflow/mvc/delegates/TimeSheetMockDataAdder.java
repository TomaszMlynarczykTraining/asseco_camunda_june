package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.processes.TimesheetProcess;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("timeSheetMockDataAdder")
@Slf4j
public class TimeSheetMockDataAdder implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getVariables().put(TimesheetProcess.FORM_DATE, LocalDate.now());
        delegateExecution.getVariables().put(TimesheetProcess.OVER_HOURS_COUNT, 5);
    }
}
