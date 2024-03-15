package me.shijunjiee.camundastudyexternal.camunda.external.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class ExternalTaskConfiguration {

    @Bean
//    @ExternalTaskSubscription(topicName = "try_self_repair_topic", processDefinitionKey = "external_task_test", lockDuration = 50000)
    @ExternalTaskSubscription(topicName = "try_self_repair_topic", lockDuration = 50000)
    public ExternalTaskHandler doSelfRepair() {

        return (externalTask, externalTaskService) -> {
            log.info("外部任务尝试自修");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Object isFree = externalTask.getVariable("isFree");
            if (isFree == null || (boolean) isFree) {
                log.info("免费维修");
                externalTaskService.handleFailure(externalTask, "维修是免费的，不用自修了", "因为维修是免费的，所以不需要浪费时间自修", 0, 5000); // 想要控制端报Incidents，retries必须设置为0
            } else {
                log.info("收费维修");
                externalTaskService.complete(externalTask);
            }
        };
    }

    @Bean
//    @ExternalTaskSubscription(topicName = "checkNegtiveTopic", processDefinitionKey = "Process_parallel_test", lockDuration = 50000)
    @ExternalTaskSubscription(topicName = "checkNegtiveTopic", lockDuration = 50000)
    public ExternalTaskHandler doCheckNegtive() {
        return (externalTask, externalTaskService) -> {
            log.info("进入检查视频是否负面");
            Object videoName = externalTask.getVariable("videoName");
            log.info("进入检查视频是否负面,检测到视频名称:{}", videoName);
            externalTaskService.complete(externalTask);
        };
    }

    @Bean
//    @ExternalTaskSubscription(topicName = "wechatPayTopic", processDefinitionKey = "Process_message_event_test", lockDuration = 50000)
    @ExternalTaskSubscription(topicName = "wechatPayTopic", processDefinitionKey = "Process_pool_lane", lockDuration = 50000)
    public ExternalTaskHandler doWechatPay() {
        return (externalTask, externalTaskService) -> {
            log.info("开始微信支付,businessKey:{}, variables:{}", externalTask.getBusinessKey(), externalTask.getAllVariables());
            externalTaskService.complete(externalTask);
        };
    }

    @Bean
//    @ExternalTaskSubscription(topicName = "alipayTopic", processDefinitionKey = "Process_message_event_test", lockDuration = 50000)
    @ExternalTaskSubscription(topicName = "alipayTopic", processDefinitionKey = "Process_pool_lane", lockDuration = 50000)
    public ExternalTaskHandler doAlyPay() {
        return (externalTask, externalTaskService) -> {
            log.info("开始支付宝支付,businessKey:{}, variables:{}", externalTask.getBusinessKey(), externalTask.getAllVariables());
            externalTaskService.complete(externalTask);
        };
    }

}
