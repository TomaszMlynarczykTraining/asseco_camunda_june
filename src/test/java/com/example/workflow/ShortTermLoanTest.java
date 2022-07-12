package com.example.workflow;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.group3.Group3ProcessVariables.PROCESS_KEY;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@Deployment(resources = {
        "processes/PR_SHORT_TERM_LOAN_PROCESS.bpmn"
})
public class ShortTermLoanTest extends AbstractProcessEngineRuleTest {
    @Test
    public void path1Test(){
        DelegateExpressions.registerJavaDelegateMock("getDebtDataDelegate").onExecutionSetVariable("applicable",true);
        MockitoAnnotations.openMocks(this);

        Map<String, Object> variables = new HashMap<>();
        variables.put("clientId", 2L);
        ProcessInstance instance = runtimeService().startProcessInstanceByKey(PROCESS_KEY, variables);
        assertThat(instance).isStarted();

        assertThat(instance).hasPassed("Activity_1gq31o4");

        Task task = taskService().createTaskQuery().processInstanceId(instance.getProcessInstanceId()).singleResult();
        assertThat(task).hasDefinitionKey("Activity_06cs59l");
        execute(job("Event_1nioyj6"));
        assertThat(instance).isEnded();

    }

}
