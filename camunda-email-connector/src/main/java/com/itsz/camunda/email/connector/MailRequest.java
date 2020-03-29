package com.itsz.camunda.email.connector;

import org.camunda.connect.impl.AbstractConnectorRequest;
import org.camunda.connect.spi.Connector;

import javax.imageio.stream.ImageInputStream;

public class MailRequest extends AbstractConnectorRequest<MailResponse> {

    protected static final String PARAM_FROM = "from";
    protected static final String PARAM_FROM_ALIAS = "fromAlias";
    protected static final String PARAM_TO = "to";
    protected static final String PARAM_SUBJECT = "subject";
    private static final String PARAM_CONTENT = "content";

    private final MailConfiguration mailConfiguration;

    public MailRequest(Connector connector) {
        super(connector);
        mailConfiguration = MailConfigurationFactory.getConfiguration();
    }

    public MailRequest setFrom(String from) {
        setRequestParameter(PARAM_FROM, from);
        return this;
    }

    public String from() {
        String from = getRequestParameter(PARAM_FROM);
        if (from == null) {
            from = mailConfiguration.getFrom();
        }
        return from;
    }

    public String getFromAlias(){
        String fromAlias = getRequestParameter(PARAM_FROM_ALIAS);
        if (fromAlias == null){
            fromAlias = mailConfiguration.getFromAlias();
        }
        return fromAlias;
    }

    public String to() {
        String to = getRequestParameter(PARAM_TO);
        if (to == null){
            to = mailConfiguration.getTo();
        }
        return to;
    }

    public String subject() {
        String subject = getRequestParameter(PARAM_SUBJECT);
        if (subject == null){
             subject = mailConfiguration.getSubject();
        }
        return subject;
    }

    public String content() {
        String content = getRequestParameter(PARAM_CONTENT);
        if (content == null){
            content = mailConfiguration.getContent();
        }
        return content;
    }
}
