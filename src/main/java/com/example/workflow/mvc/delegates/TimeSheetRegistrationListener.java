package com.example.workflow.mvc.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("timeSheetRegistrationListener")
@Slf4j
public class TimeSheetRegistrationListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        Date due = new Date();
        log.info("Ustawiam due data na:{}", due);
        delegateTask.setDueDate(due);
    }
}
