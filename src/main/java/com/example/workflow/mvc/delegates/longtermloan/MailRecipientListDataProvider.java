package com.example.workflow.mvc.delegates.longtermloan;

import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.processes.longtermloan.LongTermLoanProcess;
import com.example.workflow.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailRecipientListDataProvider implements JavaDelegate {
    private final UserService userService;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<User> users = userService.getAllUsers().stream()
                .collect(Collectors.toList());

        execution.setVariable(LongTermLoanProcess.MAIL_RECIPIENT_LIST, users);

    }
}
