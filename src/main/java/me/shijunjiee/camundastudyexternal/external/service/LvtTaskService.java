package me.shijunjiee.camundastudyexternal.external.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Slf4j
public class LvtTaskService {

    @Bean
    @ExternalTaskSubscription(topicName = "lvt1", processDefinitionKeyIn = {"Process_localvariable_test"}, lockDuration = 20000)
    public ExternalTaskHandler LvtTask1() {
        return (externalTask, externalTaskService) -> {
            log.info("---------LvtTask1-----------");

            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.forEach((k, v) -> log.info(k + " : " + v));

            externalTaskService.complete(externalTask);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "lvt11", processDefinitionKeyIn = {"Process_localvariable_test2"}, lockDuration = 20000)
    public ExternalTaskHandler LvtTask11() {
        return (externalTask, externalTaskService) -> {
            log.info("---------LvtTask1-----------");

            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.forEach((k, v) -> log.info(k + " : " + v));

            externalTaskService.complete(externalTask, null, Map.of("lvt11", "lvt11"));
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "lvt2", processDefinitionKeyIn = {"Process_localvariable_test"}, lockDuration = 20000)
    public ExternalTaskHandler LvtTask2() {
        return (externalTask, externalTaskService) -> {
            log.info("---------LvtTask2-----------");

            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.forEach((k, v) -> log.info(k + " : " + v));

            externalTaskService.complete(externalTask);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "lvt22", processDefinitionKeyIn = {"Process_localvariable_test2"}, lockDuration = 20000)
    public ExternalTaskHandler LvtTask22() {
        return (externalTask, externalTaskService) -> {
            log.info("---------LvtTask2-----------");

            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.forEach((k, v) -> log.info(k + " : " + v));

            externalTaskService.complete(externalTask);
        };
    }

}
