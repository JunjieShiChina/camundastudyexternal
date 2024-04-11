package me.shijunjiee.camundastudyexternal.external.service;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ExternalTaskSubscription(topicName = "dispatchTask", processDefinitionKeyIn = {"process_aysc"}, includeExtensionProperties = true, lockDuration = 20000)
public class DispatchTaskHandler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        System.out.println("Dispatch task handler");
        Map<String, Object> allVariables = externalTask.getAllVariables();
        allVariables.forEach((k, v) -> System.out.println(k + " : " + v));

        // 获取扩展属性
        Map<String, String> extensionProperties = externalTask.getExtensionProperties();
        extensionProperties.forEach((k, v) -> System.out.println(k + " : " + v));

        externalTaskService.complete(externalTask);
    }
}
