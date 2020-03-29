package com.itsz.camunda.email.connector;

import org.camunda.connect.spi.ConnectorResponse;

import java.util.Map;

public class MailResponse implements ConnectorResponse {


    @Override
    public Map<String, Object> getResponseParameters() {
        return null;
    }

    @Override
    public <V> V getResponseParameter(String s) {
        return null;
    }
}
