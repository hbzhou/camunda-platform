package com.itsz.camunda.email;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.connect.Connectors;
import org.camunda.connect.spi.Connector;
import org.camunda.connect.spi.ConnectorRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@EnableProcessApplication
public class CamundaEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaEmailApplication.class, args);
        Set<Connector<? extends ConnectorRequest<?>>> availableConnectors = Connectors.getAvailableConnectors();
        availableConnectors.stream().forEach(connector -> {
            System.out.println(connector.getId());
        });
    }
}
