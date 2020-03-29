package com.itsz.camunda.email;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaEmailApplication.class, args);
    }
}
