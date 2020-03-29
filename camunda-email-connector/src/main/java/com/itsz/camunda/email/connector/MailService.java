package com.itsz.camunda.email.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    private  Session session;

    private final MailConfiguration configuration;

    public MailService(MailConfiguration configuration) {
        this.configuration = configuration;
    }

    public Session getSession() {
        if (session == null) {
            session = Session.getInstance(configuration.getProperties());
        }
        return session;
    }

    public Transport getTransport() throws MessagingException {
        Transport transport = getSession().getTransport();
        if (!transport.isConnected()) {
            if (configuration.isNeedAuth()) {
                transport.connect(configuration.getUsername(), configuration.getPassWord());
            } else {
                transport.connect();
            }
        }
        return transport;
    }


}
