package me.shijunjiee.camundastudyexternal.external.service;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ExternalTaskSubscription(topicName = "dispatchTask2", processDefinitionKeyIn = {"Process_sync2"}
        , includeExtensionProperties = true, lockDuration = 20000)
public class DispatchTaskHandler2 extends BaseExternalTaskHandler {

    @Override
    protected void handleExecute() {
        System.out.println("Dispatch task handler");
        Map<String, Object> allVariables = getAllVariables();
        allVariables.forEach((k, v) -> System.out.println(k + " : " + v));

        // 获取扩展属性
        Map<String, String> extensionProperties = getExtensionProperties();
        extensionProperties.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
