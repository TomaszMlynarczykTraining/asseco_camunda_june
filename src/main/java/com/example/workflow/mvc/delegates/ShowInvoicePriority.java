package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ShowInvoicePriority implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String invoicePriority = (String)delegateExecution.getVariable("invoicePriority");

        System.out.println("Invoice priority: " + invoicePriority);
    }
}
