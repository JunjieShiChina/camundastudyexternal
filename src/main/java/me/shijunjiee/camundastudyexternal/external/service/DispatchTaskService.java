//package me.shijunjiee.camundastudyexternal.external.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
//import org.camunda.bpm.client.task.ExternalTaskHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class DispatchTaskService {
//
//    @Bean
//    @ExternalTaskSubscription(topicName = "dispatchTask", processDefinitionKeyIn = {"process_aysc"}, lockDuration = 20000)
//    public ExternalTaskHandler maleHealthPlan() {
//        return (externalTask, externalTaskService) -> {
//            log.info("---------开始男士体检-----------");
//
//            externalTaskService.complete(externalTask);
//        };
//    }
//
//}
