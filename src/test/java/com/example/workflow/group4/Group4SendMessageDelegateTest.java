package com.example.workflow.group4;

import org.camunda.bpm.cockpit.plugin.test.application.TestProcessEngineProvider;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.community.mockito.ProcessExpressions;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.camunda.community.mockito.service.RuntimeServiceFluentMock;
import org.junit.Test;
import org.mockito.Mockito;

public class Group4SendMessageDelegateTest {

    @Test
    public void testDelegate() throws Exception {
        Group4SendMessageDelegate delegate = new Group4SendMessageDelegate();
        RuntimeService runtimeService = Mockito.mock(RuntimeService.class);
        ProcessEngine processEngine = Mockito.mock(ProcessEngine.class);
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        Mockito.when(delegateExecution.getProcessEngine()).thenReturn(processEngine);
        Mockito.when(processEngine.getRuntimeService()).thenReturn(runtimeService);
        final MessageCorrelationBuilder correlation = ProcessExpressions.mockMessageCorrelation(runtimeService, "Message_zacznij_alternatywny_proces");

        delegate.execute(delegateExecution);

        Mockito.verify(correlation).correlateWithResult();


    }
}
