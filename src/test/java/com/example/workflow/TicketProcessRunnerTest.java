package com.example.workflow;

import com.example.workflow.mvc.delegates.MockDelegate;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.workflow.mvc.processes.TicketReservationProcess.PROCESS_KEY;
import static com.example.workflow.mvc.processes.TicketReservationProcess.USER_TASK_CHECK_TICKET;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@Deployment(resources = {
        "processes/ticket_price.bpmn"
})

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class TicketProcessRunnerTest {

    @Autowired
    RuntimeService runtimeService;

    @Test
    public void happyPathTest(){
        DelegateExpressions.registerJavaDelegateMock("mockDelegate");

        //ProcessExpressions.registerCallActivityMock("DefinitionKey").onExecutionAddVariables();

        MockitoAnnotations.openMocks(this);


        ProcessInstance instance = runtimeService.startProcessInstanceByKey(PROCESS_KEY);
        assertThat(instance).isStarted();


        execute(job("Event_0wu6wve"));
        assertThat(instance).hasPassed("Event_0wu6wve");
        runtimeService().correlateMessage("EXAMPLE_MESSAGE");

        assertThat(instance).isWaitingAt(USER_TASK_CHECK_TICKET);

        Task task = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();

        complete(task);

        assertThat(instance).hasPassed("Activity_1thmhve");

        assertThat(instance).isEnded();
    }
}
