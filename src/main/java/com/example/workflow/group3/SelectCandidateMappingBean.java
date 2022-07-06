package com.example.workflow.group3;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

@Service("selectCandidateMappingGroup3Bean")
public class SelectCandidateMappingBean implements DelegateVariableMapping {
    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {


    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        Object tresc = variableScope.getVariable(Group3ProcessVariables.OUTPUT_TRESC_OGLOSZENIA);
        if(tresc == null){
            tresc = "Przykladowa tresc";
        }
        delegateExecution.setVariable(Group3ProcessVariables.OUTPUT_TRESC_OGLOSZENIA, tresc);
    }
}
