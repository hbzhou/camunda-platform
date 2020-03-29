package com.itsz.camunda.email.connector;

import java.io.IOException;
import java.util.Properties;

public class PropertiesMailConfiguration implements MailConfiguration {

    public static final String USERNAME = "mail.user";
    public static final String FROM = "mail.from";
    public static final String TO = "mail.to";
    public static final String FROM_ALIAS = "mail.from.alias";
    public static final String PASSWORD = "mail.password";
    public static final String NEED_AUTH = "mail.smtp.auth";
    public static final String SUBJECT = "mail.subject";
    private static final String CONTENT = "mail.content";


    private Properties properties;

    @Override
    public Properties getProperties() {
        if (properties == null) {
            properties = loadProperties();
        }
        return properties;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/mail-config.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find mail configuration mail-config.properties ");
        }
        return properties;
    }

    @Override
    public boolean isNeedAuth() {
        return Boolean.valueOf(getProperties().getProperty(NEED_AUTH, "false"));
    }

    @Override
    public String getUsername() {
        return getProperties().getProperty(USERNAME);
    }

    @Override
    public String getPassWord() {
        return getProperties().getProperty(PASSWORD);
    }

    @Override
    public String getFrom() {
        return getProperties().getProperty(FROM);
    }

    @Override
    public String getFromAlias() {
        return getProperties().getProperty(FROM_ALIAS);
    }

    @Override
    public String getTo() {
        return getProperties().getProperty(TO);
    }

    @Override
    public String getSubject() {
        return getProperties().getProperty(SUBJECT);
    }

    @Override
    public String getContent() {
        return getProperties().getProperty(CONTENT, "");
    }

    public static void main(String[] args) {
        MailConfiguration mailConfiguration = new PropertiesMailConfiguration();
        Properties properties = mailConfiguration.getProperties();
        System.out.println(properties);
    }
}
