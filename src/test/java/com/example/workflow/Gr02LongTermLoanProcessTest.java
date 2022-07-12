package com.example.workflow;

import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@Deployment(resources = {
        "processes/GR02_long_term_loan.bpmn"
})
public class Gr02LongTermLoanProcessTest extends AbstractProcessEngineRuleTest  {

    @Test
    public void happyPathTest(){
        DelegateExpressions.registerJavaDelegateMock("getDebtData");

        DelegateExpressions.registerJavaDelegateMock("mailRecipientListDataProvider")
                .onExecutionSetVariable(LongTermLoanProcess.MAIL_RECIPIENT_LIST, new ArrayList<>());

        DelegateExpressions.registerJavaDelegateMock("mailSender");

        DelegateExpressions.registerJavaDelegateMock("prevLoansDataProvider")
                .onExecutionSetVariable(LongTermLoanProcess.IS_USER_APPLICABLE, "true");

        //ProcessExpressions.registerCallActivityMock("DefinitionKey").onExecutionAddVariables();

        MockitoAnnotations.openMocks(this);

        ProcessInstance instance = runtimeService().startProcessInstanceByKey(LongTermLoanProcess.PROCESS_KEY);
        assertThat(instance).isStarted();

        assertThat(instance).isWaitingAt("Task_0jwfrui");
        Task task = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();
        complete(task);

        assertThat(instance).isWaitingAt("Task_0268w0q");
        task = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();
        complete(task);

        assertThat(instance).isEnded();
    }
}


