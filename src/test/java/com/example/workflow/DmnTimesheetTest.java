package com.example.workflow;

import com.example.workflow.mvc.processes.TimesheetProcess;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.test.DmnEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(JUnit4.class)
public class DmnTimesheetTest {
    @ClassRule
    public static final DmnEngineRule dmnEngineRule = new DmnEngineRule();
    public static DmnEngine dmnEngine;
    public static DmnDecision decision;

    @BeforeClass
    public static void beforeAll() {
        dmnEngine = dmnEngineRule.getDmnEngine();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("processes/GR02_PR_001_TIMESHEET_DMN.dmn");
        decision = dmnEngine.parseDecision("GR02_PR_001_TIMESHEET_DMN", inputStream);
    }

    @Test
    public void shouldAcceptTimesheet() {
        //given
        DmnDecisionTableResult results = evalDmn(10, LocalDate.of(2000, 11, 10));
        //than
        assertEquals(TimesheetProcess.ACCEPTED, results.getSingleResult().getSingleEntry());
    }

    @Test
    public void shouldRejectTimesheetWhenDay30() {
        //given
        DmnDecisionTableResult results = evalDmn(10, LocalDate.of(2000, 11, 30));
        //than
        assertEquals(TimesheetProcess.REJECTED, results.getSingleResult().getSingleEntry());
    }

    @Test
    public void shouldRejectTimesheetWhenOver20Days() {
        //given
        DmnDecisionTableResult results = evalDmn(21, LocalDate.of(2000, 11, 1));
        //than
        assertEquals(TimesheetProcess.REJECTED, results.getSingleResult().getSingleEntry());
    }

    @Test
    public void shouldRejectTimesheetWhenOver20DaysAndDay31() {
        //given
        DmnDecisionTableResult results = evalDmn(21, LocalDate.of(2000, 12, 31));
        //than
        assertEquals(TimesheetProcess.REJECTED, results.getSingleResult().getSingleEntry());
    }

    private DmnDecisionTableResult evalDmn(int daysOVer, LocalDate date) {
        VariableMap variables = Variables.createVariables()
                .putValue(TimesheetProcess.OVER_HOURS_COUNT, daysOVer)
                .putValue(TimesheetProcess.FORM_DATE, date);
        return dmnEngine.evaluateDecisionTable(decision, variables);

    }
}
