package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.*;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitializeVariablesOnListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setVariable("dataValid","FALSE");
        Object termType = delegateTask.getVariable("termType");
        if (termType == null){
            delegateTask.setVariable("termType","");
        }
    }
}
