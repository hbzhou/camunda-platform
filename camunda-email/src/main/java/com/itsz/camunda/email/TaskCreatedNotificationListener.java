package com.itsz.camunda.email;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TaskCreatedNotificationListener implements TaskListener {

    private static final Logger LOGGER = Logger.getLogger(TaskCreatedNotificationListener.class.getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void notify(DelegateTask delegateTask) {

        String assignee = delegateTask.getAssignee();
        String taskId = delegateTask.getId();

        if (assignee != null) {

            // Get User Profile from User Management
            IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
            User user = identityService.createUserQuery().userId(assignee).singleResult();

            if (user != null) {

                // Get Email Address from User Profile
                String recipient = user.getEmail();

                if (recipient != null && !recipient.isEmpty()) {
                    try {
                        SimpleMailMessage mailMessage = new SimpleMailMessage();
                        mailMessage.setFrom("jeremygilbert@126.com");
                        mailMessage.setSubject("Task Notification : Task assigned  to " + delegateTask.getName());
                        mailMessage.setTo(recipient);
                        mailMessage.setCc("Jeremy_Zhou@epam.com");
                        mailMessage.setText("Please complete: http://localhost:8080/camunda/app/tasklist/default/#/task=" + taskId);
                        javaMailSender.send(mailMessage);

                        LOGGER.info("Task Assignment Email successfully sent to user '" + assignee + "' with address '" + recipient + "'.");

                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
                    }

                } else {
                    LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
                }

            } else {
                LOGGER.warning("Not sending email to user " + assignee + "', user is not enrolled with identity service.");
            }

        }


    }
}
