package com.itsz.camunda.email.connector;

public class MailConfigurationFactory {

    private static MailConfiguration mailConfiguration;

    private MailConfigurationFactory() {
    }

    public static MailConfiguration getConfiguration() {
        if (mailConfiguration == null) {
            mailConfiguration = new PropertiesMailConfiguration();
        }
        return mailConfiguration;
    }
}
