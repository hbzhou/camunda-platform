package com.itsz.camunda.email.connector;

import org.camunda.connect.impl.AbstractConnector;
import org.camunda.connect.spi.ConnectorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;


public class MailConnector extends AbstractConnector<MailRequest, MailResponse> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailConnector.class);

    public static final String CONNECTOR_ID = "mail-send";

    public MailConnector() {
        super(CONNECTOR_ID);
    }

    @Override
    public MailRequest createRequest() {
        return new MailRequest(this);
    }

    @Override
    public ConnectorResponse execute(MailRequest mailRequest) {
        MailService mailService = MailServiceFactory.getService();
        try {
            Message message = createMessage(mailService.getSession(), mailRequest);
            Transport transport = mailService.getTransport();
            transport.sendMessage(message, message.getAllRecipients());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new MailResponse();
    }

    private Message createMessage(Session session, MailRequest mailRequest) throws UnsupportedEncodingException, MessagingException {
        // 1. 创建一封邮件
        Message message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(mailRequest.from(), mailRequest.getFromAlias(), MailConfiguration.DEFAULT_CHARSET));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailRequest.to(), "", MailConfiguration.DEFAULT_CHARSET));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(mailRequest.subject());

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(mailRequest.content(), MailConfiguration.TEXT_HTML_CHARSET_UTF_8);

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
