package com.example.workflow.group3;


import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrepareCandidateListDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        // System.out.println(delegateExecution.getVariable("pizza"));
        // delegateExecution.getSuperExecution().getVariable("pizza");
        // delegateExecution.getVariableLocal("pizza");

        List<Candidate> candidates = prepareCandidates();

        delegateExecution.setVariable(Group3ProcessVariables.VAR_CANDIDATE_LIST, candidates);

        //System.out.println(runtimeService.getVariable(delegateExecution.getProcessInstanceId(), "pizza"));


    }

    private List<Candidate> prepareCandidates() {
        List<Candidate> result = new ArrayList<>();
        result.add(new Candidate("Candidate 1"));
        result.add(new Candidate("Candidate 2"));
        result.add(new Candidate("Candidate 3"));
        return result;
    }

}