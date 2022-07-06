package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class FakturyDoDostawDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Map<String, Object> variables = delegateExecution.getVariables();
        //List<String> listaDostaw = Arrays.asList("zam1","zam2","zam3","zam4");

        String dostawa = (String)variables.get("dostawa");
        System.out.println("Pocztek generowania dla dostawa="+dostawa);
        Thread.sleep(5000);
        System.out.println("Koniec generowania dla dostawa="+dostawa);
    }
}
