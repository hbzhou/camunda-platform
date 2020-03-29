package com.itsz.camunda.email.connector;

import java.util.Properties;

public interface MailConfiguration {

    String DEFAULT_CHARSET = "UTF-8";
    String TEXT_HTML_CHARSET_UTF_8 = "text/html;charset=UTF-8";

    Properties getProperties();

    boolean isNeedAuth();

    String getUsername();

    String getPassWord();

    String getFrom();

    String getFromAlias();

    String getTo();

    String getSubject();

    String getContent();
}
