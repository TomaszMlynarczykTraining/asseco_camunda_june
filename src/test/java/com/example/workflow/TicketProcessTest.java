package com.example.workflow;

import com.example.workflow.mvc.delegates.MockDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.ProcessExpressions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.workflow.mvc.processes.TicketReservationProcess.PROCESS_KEY;
import static com.example.workflow.mvc.processes.TicketReservationProcess.USER_TASK_CHECK_TICKET;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@Deployment(resources = {
        "processes/ticket_price.bpmn"
})
public class TicketProcessTest extends AbstractProcessEngineRuleTest {

    @Mock
    MockDelegate mockDelegate;

    @Test
    public void happyPathTest(){
        DelegateExpressions.registerJavaDelegateMock("mockDelegate");

        //ProcessExpressions.registerCallActivityMock("DefinitionKey").onExecutionAddVariables();

        MockitoAnnotations.openMocks(this);


        ProcessInstance instance = runtimeService().startProcessInstanceByKey(PROCESS_KEY);

        assertThat(instance).isStarted();
        assertThat(instance).isWaitingAt(USER_TASK_CHECK_TICKET);

        Task task = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();

        complete(task);

        assertThat(instance).hasPassed("Activity_1thmhve");

        assertThat(instance).isEnded();
    }
}
