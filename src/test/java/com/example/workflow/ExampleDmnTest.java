package com.example.workflow;


import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Assert;
import org.junit.Test;

@Deployment(resources = {
        "processes/TEST_DMN.dmn"
})
public class ExampleDmnTest extends AbstractProcessEngineRuleTest {

    @Test
    public void dmnHappyPath(){
        DmnDecisionTableResult dmnDecisionRuleResults = processEngine.getDecisionService()
                .evaluateDecisionTableByKey("Decision_0fjsi5x",
                        Variables.createVariables().putValue("pizzaType", "MARGARITHA"));

        Assert.assertEquals("true", dmnDecisionRuleResults.getSingleResult().getFirstEntry() );

    }
}
