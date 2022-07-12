package com.example.workflow;

import com.example.workflow.group3.GetDebtDataDelegate;
import com.example.workflow.mvc.delegates.MockDelegate;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.example.workflow.group3",
        "com.example.workflow.mvc.entity"})
@TestPropertySource("classpath:application.yaml")
@EnableJpaRepositories("com.example.workflow.mvc.repository")
public class GetDebtDataDelegateTest {



    @Mock
    ClientService clientService;

    @Test
    public void testDelegate() throws Exception {

        Map<String, Object> variables = new HashMap<>();
        variables.put("clientId", 2L);
        GetDebtDataDelegate getDebtDataDelegate = new GetDebtDataDelegate();
        DelegateExecutionFake execution = DelegateExecutionFake
                .of()
                .withBusinessKey("someBusinessKey")
                .withVariables(variables);


        getDebtDataDelegate.execute(execution);

        // to sie nie wywola, bo delegat nie ma logiki
        // Assert.assertEquals("ast",execution.getVariable("somevar"));

    }
}
