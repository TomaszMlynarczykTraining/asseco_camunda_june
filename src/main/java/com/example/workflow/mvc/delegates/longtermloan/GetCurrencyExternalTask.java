package com.example.workflow.mvc.delegates.longtermloan;

import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@ExternalTaskSubscription("longTermLoanGetCurrency")
public class GetCurrencyExternalTask implements ExternalTaskHandler {


    private static final String RESOURCE_TEMPLATE = "https://api.nbp.pl/api/exchangerates/rates/a/#CURRENCY#?format=json";

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        RestTemplate restTemplate = new RestTemplate();
        String currency = externalTask.getVariable(LongTermLoanProcess.CURRENCY);
        String res = RESOURCE_TEMPLATE.replace("#CURRENCY#", currency);
        NbpResponse resp = restTemplate.getForObject(res, NbpResponse.class);
        VariableMap vars = Variables.createVariables().putValue("RATE", resp.getRates().get(0).getMid());
        externalTaskService.complete(externalTask, vars);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class NbpResponse {
        private List<Rate> rates;
        private String table;
        private String currency;
        private String code;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class Rate {
        private String no;
        private LocalDate effectiveDate;
        private BigDecimal mid;
    }
}
