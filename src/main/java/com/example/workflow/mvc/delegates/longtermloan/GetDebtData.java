package com.example.workflow.mvc.delegates.longtermloan;


import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import com.example.workflow.mvc.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetDebtData implements JavaDelegate {
    private final ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Object id = delegateExecution.getVariable(LongTermLoanProcess.CLIENT_ID);
        long clientId;

        if(id == null)
            id = 1L;

        if (id instanceof String)
            clientId = Long.parseLong((String) id);
        else
            clientId = (long) id;


        Client cl = clientService.getClientById(clientId);
        delegateExecution.setVariable(LongTermLoanProcess.DEBT_DATA, cl.getDebt());

    }
}