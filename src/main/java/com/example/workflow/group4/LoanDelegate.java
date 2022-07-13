package com.example.workflow.group4;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component
public class LoanDelegate implements JavaDelegate {
    public static final String CLIENT_ID = "clientId";

    @Inject
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Boolean error = true;
        Long amount = 0L;
        int retraice = (int) delegateExecution.getVariable("retraice");

        Long clientId = (Long) delegateExecution.getVariable(CLIENT_ID);
        Optional<Client> clientOpt = clientService.getClientByIdg4(clientId);
        if (clientOpt.isPresent()) {
            if(retraice==1){
                throw new RuntimeException("Wykonano zbyt wiele prob!");
            }
            retraice--;
            Client client = clientOpt.get();

            Debt clientDebt = client.getDebt();
            if (clientDebt != null) {
                error = false;
                amount = Long.valueOf(clientDebt.getAmount());

            }
        }
        delegateExecution.setVariable("error", error);
        delegateExecution.setVariable("dept", amount);
        delegateExecution.setVariable("retraice", retraice);
    }
}
