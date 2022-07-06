package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapDelegate implements DelegateVariableMapping {
    @Override
    public void mapInputVariables(DelegateExecution superExecution, VariableMap subVariables) {

        Map<String, Object> variables = superExecution.getVariables();

        subVariables.put("var1","var");
    }

    @Override
    public void mapOutputVariables(DelegateExecution superExecution, VariableScope subInstance) {

        Map<String, Object> variables = superExecution.getVariables();
        Map<String, Object> variables1 = subInstance.getVariables();

        superExecution.setVariable("var2","var");

    }
}
