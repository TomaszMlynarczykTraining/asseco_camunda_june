package com.example.workflow.group4;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.mockito.Mock;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;


@Deployment(resources={
        "processes/PR_G04_proces_alternatywny.bpmn"
})
public class Group4WorkflowTest extends AbstractProcessEngineRuleTest {

    @Test
    public void happyFlowTest(){
        ProcessInstance instance = runtimeService().startProcessInstanceByKey("Process_alternative");
        assertThat(instance).isStarted();

        runtimeService().correlateMessage("Message_zacznij_alternatywny_proces");

        assertThat(instance).isWaitingAt("Activity_alternative_userTask");

        Task task = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();

        complete(task);

        assertThat(instance).isEnded();
    }

}
