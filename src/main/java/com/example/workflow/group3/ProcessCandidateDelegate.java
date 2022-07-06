package com.example.workflow.group3;


import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessCandidateDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        // System.out.println(delegateExecution.getVariable("pizza"));
        // delegateExecution.getSuperExecution().getVariable("pizza");
        // delegateExecution.getVariableLocal("pizza");

        Candidate candidate = (Candidate) delegateExecution.getVariable(Group3ProcessVariables.VAR_CANDIDATE);
        Boolean passed = (Boolean) delegateExecution.getVariable(Group3ProcessVariables.VAR_CANDIDATE_PASSED);
        if(passed){
            candidate.setSelected(true);
            delegateExecution.setVariable(Group3ProcessVariables.VAR_CANDIDATE, candidate);
        }
    }

}