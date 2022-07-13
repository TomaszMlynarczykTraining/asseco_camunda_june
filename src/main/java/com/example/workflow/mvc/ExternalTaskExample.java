package com.example.workflow.mvc;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("preparePDFFile")
public class ExternalTaskExample implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String businessKey = externalTask.getBusinessKey();

        //logika biznesowa, np. prztwarzanie pdfa

        VariableMap variables = Variables.createVariables().putValue("pdfId", "124125151568");

        externalTaskService.complete(externalTask, variables);

    }
}
