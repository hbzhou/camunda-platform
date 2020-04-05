package com.itsz.camunda.core;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class ErrorValidateListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("---------------------");
        throw new BpmnError("errorCode", "errorMsg");
    }
}
