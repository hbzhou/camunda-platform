package com.itsz.camunda.email.connector;

import org.camunda.connect.impl.AbstractRequestInvocation;
import org.camunda.connect.spi.ConnectorRequest;
import org.camunda.connect.spi.ConnectorRequestInterceptor;

import javax.mail.Message;
import javax.mail.Transport;
import java.util.List;

public class MailInvocation extends AbstractRequestInvocation<Message> {

    protected final MailService mailService;

    public MailInvocation(Message target, ConnectorRequest<?> request, List<ConnectorRequestInterceptor> interceptorChain, MailService mailService) {
        super(target, request, interceptorChain);
        this.mailService = mailService;
    }

    @Override
    public Object invokeTarget() throws Exception {
        Message message = target;
        Transport transport = mailService.getTransport();
        transport.sendMessage(message, message.getAllRecipients());
        return null;
    }
}
