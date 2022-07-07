package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListaDostawDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        Map<String, Object> variables = delegateExecution.getVariables();
        List<String> listaDostaw = Arrays.asList("zam1","zam2","zam3","zam4");

        delegateExecution.setVariable("listaDostaw", listaDostaw);

    }
}
