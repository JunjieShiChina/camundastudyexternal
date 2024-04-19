package me.shijunjiee.camundastudyexternal.external.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Slf4j
public class VTTaskService {

    @Bean
    @ExternalTaskSubscription(topicName = "vt1", processDefinitionKeyIn = {"Process_variable_test"}, lockDuration = 20000)
    public ExternalTaskHandler VtTask1() {
        return (externalTask, externalTaskService) -> {
            log.info("---------VtTask1-----------");

            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.forEach((k, v) -> log.info(k + " : " + v));

            VariableMap variables = Variables.createVariables();
            variables.put("vt1", "vt1");
            externalTaskService.complete(externalTask, variables);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "vt2", processDefinitionKeyIn = {"Process_variable_test"}, lockDuration = 20000)
    public ExternalTaskHandler VtTask2() {
        return (externalTask, externalTaskService) -> {
            log.info("---------VtTask2-----------");

            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.forEach((k, v) -> log.info(k + " : " + v));

            externalTaskService.complete(externalTask);
        };
    }

}
