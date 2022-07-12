package com.example.workflow;

import com.example.workflow.mvc.delegates.MockDelegate;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.MapKeyColumn;

public class MockDelegateTest {


    @Test
    public void testDelegate() throws Exception {

        MockDelegate mockDelegate = new MockDelegate();
        DelegateExecutionFake execution = DelegateExecutionFake.of().withBusinessKey("someBusinessKey");

        mockDelegate.execute(execution);

        // to sie nie wywola, bo delegat nie ma logiki
       // Assert.assertEquals("ast",execution.getVariable("somevar"));

    }
}
