package com.itsz.camunda.woker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.logging.Logger;

public class CamundaWorker {

    private final static Logger LOGGER = Logger.getLogger(CamundaWorker.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8081/rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        ExternalTaskHandler externalTaskHandler = (externalTask, externalTaskService) -> {
            // Get a process variable
            String item = externalTask.getVariable("item");
            Integer amount = externalTask.getVariable("amount");

            LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");
            // Complete the task
            externalTaskService.complete(externalTask);
        };
        // subscribe to an external task topic as specified in the process
        client.subscribe("charge-card")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler(externalTaskHandler)
                .open();

    }

}
