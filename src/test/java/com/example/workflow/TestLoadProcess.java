package com.example.workflow;

import com.example.workflow.mvc.delegates.InitializeVariablesOnListener;
import com.example.workflow.mvc.delegates.MockCheckLoansDelegate;
import com.example.workflow.mvc.delegates.MockLoansDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.dmn.instance.Variable;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.ProcessExpressions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;

@Deployment(resources = {
        "processes/loan_process.bpmn"
})
public class TestLoadProcess extends AbstractProcessEngineRuleTest {

    @Mock
    private InitializeVariablesOnListener initializeVariablesOnListener;

    @Mock
    private MockCheckLoansDelegate mockCheckLoansDelegate;

    @Mock
    private MockLoansDelegate mockLoansDelegate;

    @Test
    public void happyPathTest() {

        MockitoAnnotations.openMocks(this);

        DelegateExpressions.registerTaskListenerMock("initializeVariablesOnListener").
                onExecutionSetVariables(
                        Variables.  putValue("dataValid", "FALSE").
                                    putValue("termType", "LONG")) ;

        DelegateExpressions.registerJavaDelegateMock("mockCheckLoansDelegate").
                onExecutionSetVariable("dataValid", "TRUE");

        DelegateExpressions.registerJavaDelegateMock("mockLoansDelegate");

        ProcessInstance instance = runtimeService().startProcessInstanceByKey("LoanProcess");
        assertThat(instance).isStarted();

        // task choose options task
        assertThat(instance).isWaitingAt("Activity_1nscsjr");

        Task chooseLoanOptionsTask = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();
        complete(chooseLoanOptionsTask);

        // task fill in data
        assertThat(instance).isWaitingAt("Activity_FillInData");

        Task fillInDataTask = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();
        complete(fillInDataTask);

        // task long term
        assertThat(instance).isWaitingAt("Activity_0vrncyz");

        Task longTermTask = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();
        complete(longTermTask);

        assertThat(instance).hasPassed("Activity_SendEmailToUser");

        assertThat(instance).isEnded();
    }
}