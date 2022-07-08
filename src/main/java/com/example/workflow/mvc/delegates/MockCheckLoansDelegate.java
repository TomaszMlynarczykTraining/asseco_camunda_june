package com.example.workflow.mvc.delegates;


import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MockCheckLoansDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        // System.out.println(delegateExecution.getVariable("pizza"));
        // delegateExecution.getSuperExecution().getVariable("pizza");
        // delegateExecution.getVariableLocal("pizza");

        Object termType = delegateExecution.getVariable("termType");
        delegateExecution.setVariable("dataValid","FALSE");
        if (Arrays.asList("SHORT","LONG","HOUSE").contains(termType)){

            boolean fndOk = false;
            List<Client> clients = clientService.getClients();
            for (Client c : clients){
                if ((""+c.getId()).equals(delegateExecution.getVariable("userIdentificationNumber"))){
                    if (!c.getStreet().equals(delegateExecution.getVariable("form_street"))){
                        break;
                    }
                    if (!c.getCurrency().equals(delegateExecution.getVariable("form_phoneNumber"))){
                        break;
                    }
                    if (!c.getDeclaredIncome().equals(delegateExecution.getVariable("form_declaredIncome"))){
                        break;
                    }
                    fndOk = true;
                }
            }
            if (fndOk) {
                delegateExecution.setVariable("dataValid", "TRUE");
            }
        }

        //System.out.println(runtimeService.getVariable(delegateExecution.getProcessInstanceId(), "pizza"));


    }
}