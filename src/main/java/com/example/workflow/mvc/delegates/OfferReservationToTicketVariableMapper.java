package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.processes.OfferReservationProcess;
import javassist.compiler.ast.Variable;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.workflow.mvc.processes.OfferReservationProcess.VARIABLE_TICKET_PRICE;

@Service
public class OfferReservationToTicketVariableMapper implements DelegateVariableMapping {


    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {

        Object variable = delegateExecution.getVariable(OfferReservationProcess.INPUT_VARIABLE_OPINION);


        variableMap.putValue(OfferReservationProcess.INPUT_VARIABLE_OPINION, variable);

    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {

        Object variable = variableScope.getVariable(VARIABLE_TICKET_PRICE);

        delegateExecution.setVariable(VARIABLE_TICKET_PRICE, variable);


        //zle
       // Object variable = delegateExecution.getVariable(VARIABLE_TICKET_PRICE);
       //variableScope.setVariable(VARIABLE_TICKET_PRICE, variable);

    }
}
