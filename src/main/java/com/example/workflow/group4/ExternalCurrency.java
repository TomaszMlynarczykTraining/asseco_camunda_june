package com.example.workflow.group4;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ExternalTaskSubscription("changeCurrency")
public class ExternalCurrency implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

      String currency  = externalTask.getVariable("currency");

        RestTemplate servisRest = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/"+currency+"/last/?format=json";
        System.out.println(url);
        String result =servisRest.getForObject(url, String.class);
        System.out.println(result);

        VariableMap variables = Variables.createVariables();
        variables.put("NBP", result);
        externalTaskService.complete(externalTask);

    }
}
