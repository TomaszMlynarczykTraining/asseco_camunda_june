package com.example.workflow.mvc.delegates.longtermloan;


import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DebtDataProvider implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> vars = new HashMap<>();

        vars.put(LongTermLoanProcess.DEBT_DATA, getMockDebt(1, "10", "PLN", "false"));
        log.info("GetDebtData");
    }

    public Debt getMockDebt(long id, String amount, String currency, String isOverdue){
        Debt debt = new Debt();
        debt.setId(id);
        debt.setAmount(amount);
        debt.setCurrency(currency);
        debt.setIsOverdue(isOverdue);
        return debt;
    }
}