package com.example.workflow.mvc.delegates.longtermloan;

import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GetDebtDataTest {
    @Test
    void execute() throws Exception {
//        MockDelegate mockDelegate = new MockDelegate();

        GetDebtData getDebtData = new GetDebtData(new MockClientService(0L, 10L));
        DelegateExecutionFake execution = DelegateExecutionFake.of().withVariable(LongTermLoanProcess.CLIENT_ID, 0L);
        getDebtData.execute(execution);
        Assert.assertEquals(10L, ((Debt) execution.getVariable(LongTermLoanProcess.DEBT_DATA)).getId());
    }
}