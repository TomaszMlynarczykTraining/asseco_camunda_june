package com.example.workflow.group3;


import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessCandidatesDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        // System.out.println(delegateExecution.getVariable("pizza"));
        // delegateExecution.getSuperExecution().getVariable("pizza");
        // delegateExecution.getVariableLocal("pizza");

        List<Candidate> candidates = (List<Candidate>) delegateExecution.getVariable(Group3ProcessVariables.VAR_CANDIDATE_LIST);
        boolean isSelected = false;
        for(Candidate candidate : candidates){
            if(candidate.isSelected()){
                isSelected = true;
                break;
            }
        }
        delegateExecution.setVariable(Group3ProcessVariables.VAR_IS_SELECTED_CANDIDATE, isSelected);
    }

}