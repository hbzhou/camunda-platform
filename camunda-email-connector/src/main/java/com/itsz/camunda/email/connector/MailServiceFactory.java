package com.itsz.camunda.email.connector;


public class MailServiceFactory {

    private static MailService mailService;

    private MailServiceFactory(){};

    public static MailService getService() {
        if (mailService==null){
            mailService = new MailService(MailConfigurationFactory.getConfiguration());
        }
        return mailService;
    }

}
