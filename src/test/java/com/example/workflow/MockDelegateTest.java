package com.example.workflow;

import com.example.workflow.mvc.delegates.MockDelegate;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.camunda.community.mockito.service.RuntimeServiceFluentMock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.MapKeyColumn;

public class MockDelegateTest {

    @Test
    public void testDelegate() throws Exception {

        MockDelegate mockDelegate = new MockDelegate();


        // to sie nie wywola, bo delegat nie ma logiki
       // Assert.assertEquals("ast",execution.getVariable("somevar"));

    }
}
