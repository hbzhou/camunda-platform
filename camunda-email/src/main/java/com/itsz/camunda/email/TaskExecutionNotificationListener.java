package com.itsz.camunda.email;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TaskExecutionNotificationListener implements ExecutionListener {

    private static final Logger LOGGER = Logger.getLogger(TaskCreatedNotificationListener.class.getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String name = execution.getCurrentActivityName();
        ProcessEngine processEngine = execution.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        LOGGER.info("Activity " + name + " execution finished");

    }
}
