package me.shijunjiee.camundastudyexternal.external.service;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.Map;

public abstract class BaseExternalTaskHandler implements ExternalTaskHandler  {
    private ExternalTask externalTask;
    private ExternalTaskService externalTaskService;
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        this.externalTask = externalTask;
        this.externalTaskService = externalTaskService;
        mappingIn();
        handleExecute();
        mappingOut();
        externalTaskService.complete(externalTask);
    }

    protected abstract void handleExecute();

    // 映射inmapping
    private void mappingIn() {
        System.out.println("mappingIn");
    }

    // 映射outmapping
    private void mappingOut() {
        System.out.println("mappingOut");
    }

    protected Map<String, Object> getAllVariables() {
        return externalTask.getAllVariables();
    }

    protected Map<String, String>  getExtensionProperties() {
        return externalTask.getExtensionProperties();
    }

}
