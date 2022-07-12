package com.example.workflow.group3;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.entity.Loan;
import com.example.workflow.mvc.service.ClientService;
import com.example.workflow.mvc.service.LoanService;
import com.example.workflow.mvc.service.UserService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDebtDataDelegate implements JavaDelegate {
    @Autowired
    ClientService clientService;

    @Autowired
    LoanService loanService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");
        Client client = clientService.getClientById(clientId);
        Debt debt = client.getDebt();
        Boolean isOverdue = false;
        if(debt!=null){
            if(debt.getIsOverdue()!=null && "1".equals(debt.getIsOverdue())){
                isOverdue = true;
            }
        }
        List<Loan> loans = loanService.getLoansForClient(clientId);
        Boolean applicable = true;
        if(!loans.isEmpty() || isOverdue){
            applicable = false;
        }
        delegateExecution.setVariable("applicable", applicable);
    }

}
