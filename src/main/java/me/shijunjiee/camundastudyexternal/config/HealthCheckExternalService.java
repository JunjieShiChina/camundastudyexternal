package me.shijunjiee.camundastudyexternal.config;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HealthCheckExternalService {


    @Bean
    @ExternalTaskSubscription(topicName = "male_health_plan", processDefinitionKeyIn = {"Process_connector_test"}, lockDuration = 20000)
    public ExternalTaskHandler maleHealthPlan() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始男士体检-----------");

            externalTaskService.complete(externalTask);
        };
    }


    @Bean
    @ExternalTaskSubscription(topicName = "female_health_plan", processDefinitionKeyIn = {"Process_connector_test"}, lockDuration = 20000)
    public ExternalTaskHandler femaleHealthPlan() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始女士体检-----------");
            externalTaskService.complete(externalTask);
        };
    }

}
