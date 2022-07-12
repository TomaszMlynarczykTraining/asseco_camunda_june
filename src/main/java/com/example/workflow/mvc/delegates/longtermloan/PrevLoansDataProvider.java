package com.example.workflow.mvc.delegates.longtermloan;


import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.entity.Loan;
import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PrevLoansDataProvider implements JavaDelegate {
    public static final  Random rd = new Random();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Debt debt = (Debt) delegateExecution.getVariable(LongTermLoanProcess.DEBT_DATA);
        List<Loan> loans = getLoans();
        delegateExecution.setVariable(LongTermLoanProcess.IS_USER_APPLICABLE, evaluateUserApplicability(debt, loans));
    }

    private boolean evaluateUserApplicability(Debt debt, List<Loan> loans) {
        return false;
       //return rd.nextBoolean();
    }

    private List<Loan> getLoans(){
        return new ArrayList<>();
    }
}