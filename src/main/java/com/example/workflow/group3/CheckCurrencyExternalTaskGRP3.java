package com.example.workflow.group3;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Component
@ExternalTaskSubscription("checkCurrency")
public class CheckCurrencyExternalTaskGRP3 implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String businessKey = externalTask.getBusinessKey();
        try {
            //logika biznesowa, np. prztwarzanie pdfa
//            throw new Exception("test");
            RestTemplateBuilder builder = new RestTemplateBuilder();
            RestTemplate template = builder.build();
//        String uri = UriComponentsBuilder.fromUriString("https://api.nbp.pl/api/exchangerates/rates/A/USD/?format=json")
//                .pathSegment("")
//                .encode()
//                .toUriString();

            String url = "https://api.nbp.pl/api/exchangerates/rates/A/USD/?format=json";

            String rate = template.getForObject(url, String.class);

            VariableMap variables = Variables.createVariables().putValue("rate", rate);

            externalTaskService.complete(externalTask, variables);
        } catch(Exception ex) {
            int retries = 1;
            if(externalTask.getRetries()!=null){
                retries = retries - externalTask.getRetries();
                if (retries<0){
                    retries = 0;
                }
            }
            ex.printStackTrace();
            Long duration = Duration.parse("PT2M").toMillis();
            externalTaskService.handleFailure(externalTask.getId(), "Exception!", ExceptionUtils.getStackTrace(ex), retries, duration);
        }

    }
}
