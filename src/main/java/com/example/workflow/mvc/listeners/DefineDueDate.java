package com.example.workflow.mvc.listeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DefineDueDate implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,5);
        Date dueDate = cal.getTime();
        delegateTask.setDueDate(dueDate);
        System.out.println(delegateTask.getEventName());
    }
}
