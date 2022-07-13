package com.example.workflow.mvc;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@ExternalTaskSubscription("kursy_topic")
public class ExternalTaskGetRates implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String businessKey = externalTask.getBusinessKey();

        VariableMap variables;

        String currency = externalTask.getVariable("form_currency");
        if ("PLN".equals(currency)) {
            variables = Variables.createVariables().putValue("rates", "no response needed");
            variables.putValue("mid", Double.parseDouble("1"));
        } else {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            Map<String, String> params = new HashMap<String, String>();

            HttpEntity entity = new HttpEntity(headers);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> response = restTemplate.exchange("http://api.nbp.pl/api/exchangerates/rates/A/"+currency+"/", HttpMethod.GET, entity, String.class, params);

            Double mid = null;
            try {
                JSONObject json = new JSONObject(response.getBody());
                int i = 1;
                JSONArray ratesArray = (JSONArray) json.get("rates");
                JSONObject rate = (JSONObject) ratesArray.get(0);
                mid = (Double) rate.get("mid");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            variables = Variables.createVariables().putValue("rates", response.getBody());
            variables.putValue("mid", mid);
        }
        externalTaskService.complete(externalTask, variables);

    }
}
