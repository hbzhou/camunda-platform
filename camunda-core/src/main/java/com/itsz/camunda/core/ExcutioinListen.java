package com.itsz.camunda.core;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class ExcutioinListen implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        throw new BpmnError("errorCode", "errorMsg");

    }
}
