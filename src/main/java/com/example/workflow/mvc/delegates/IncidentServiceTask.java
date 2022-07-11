package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.rest.DeploymentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncidentServiceTask implements JavaDelegate {

    @Autowired
    ClientService clientService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Debt debt = clientService.getClients().get(1).getDebt();



    }}
