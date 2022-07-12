package com.example.workflow.mvc.delegates.longtermloan;

import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSender implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        User user = (User) execution.getVariable(LongTermLoanProcess.MAIL_RECIPIENT);
        if(user != null)
            log.info("Sending email to userId:{}", user.getId());
        else throw new BpmnError("Cannot send email to null user");


    }
}
