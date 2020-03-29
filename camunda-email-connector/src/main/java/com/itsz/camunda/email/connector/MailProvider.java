package com.itsz.camunda.email.connector;

import org.camunda.connect.spi.Connector;
import org.camunda.connect.spi.ConnectorProvider;

public class MailProvider implements ConnectorProvider {

    @Override
    public String getConnectorId() {
        return MailConnector.CONNECTOR_ID;
    }

    @Override
    public Connector<?> createConnectorInstance() {
        return new MailConnector();
    }
}
