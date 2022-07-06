package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.processes.TimesheetProcess;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("variableMapperOcenaWniosku")
public class VariableMapperOcenaWniosku implements DelegateVariableMapping {


    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        Map<String, Object>  vars = delegateExecution.getVariables();
        variableMap.put(TimesheetProcess.OVER_HOURS_COUNT, vars.get(TimesheetProcess.OVER_HOURS_COUNT));
        variableMap.put(TimesheetProcess.EMPLOYEE_NAME, vars.get(TimesheetProcess.EMPLOYEE_NAME));
        variableMap.put(TimesheetProcess.OVER_HOURS_FORM_ID, vars.get(TimesheetProcess.OVER_HOURS_FORM_ID));
    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        Map<String, Object>  vars = variableScope.getVariables();
        delegateExecution.getVariables().put(TimesheetProcess.OVER_HOURS_COUNT, vars.get(TimesheetProcess.OVER_HOURS_COUNT));
        delegateExecution.getVariables().put(TimesheetProcess.EMPLOYEE_NAME, vars.get(TimesheetProcess.EMPLOYEE_NAME));
        delegateExecution.getVariables().put(TimesheetProcess.OVER_HOURS_FORM_ID, vars.get(TimesheetProcess.OVER_HOURS_FORM_ID));
    }
}
