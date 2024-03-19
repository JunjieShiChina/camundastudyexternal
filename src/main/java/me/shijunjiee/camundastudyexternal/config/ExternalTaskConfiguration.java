package me.shijunjiee.camundastudyexternal.config;

import camundajar.impl.com.google.gson.JsonObject;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.shijunjiee.camundastudyexternal.spin.CustomMsg;
import me.shijunjiee.camundastudyexternal.spin.ProductType;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.impl.util.JsonUtil;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class ExternalTaskConfiguration {

//    @Bean("doSelfRepair")
//    @ExternalTaskSubscription(topicName = "try_self_repair_topic", processDefinitionKey = "external_task_test", lockDuration = 50000)
////    @ExternalTaskSubscription(topicName = "try_self_repair_topic", lockDuration = 50000)
//    public ExternalTaskHandler doSelfRepair() {
//
//        return (externalTask, externalTaskService) -> {
//            log.info("外部任务尝试自修");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            Object isFree = externalTask.getVariable("isFree");
//            if (isFree == null || (boolean) isFree) {
//                log.info("免费维修");
//                externalTaskService.handleFailure(externalTask, "维修是免费的，不用自修了", "因为维修是免费的，所以不需要浪费时间自修", 0, 5000); // 想要控制端报Incidents，retries必须设置为0
//            } else {
//                log.info("收费维修");
//                externalTaskService.complete(externalTask);
//            }
//        };
//    }

    @Bean("doSelfRepair2")
    @ExternalTaskSubscription(topicName = "try_self_repair_topic", processDefinitionKey = "Process_spinjson_test", lockDuration = 50000)
//    @ExternalTaskSubscription(topicName = "try_self_repair_topic", lockDuration = 50000)
    public ExternalTaskHandler doSelfRepair2() {

        return (externalTask, externalTaskService) -> {
            log.info("外部任务尝试自修");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            ProductType productType = new ProductType();
            productType.setBrand("海尔");
            productType.setPower(3);
            productType.setModel("HAIER_205");
            CustomMsg customMsg = new CustomMsg();
            customMsg.setName("junjie");
            customMsg.setProduct("冰箱");
            customMsg.setProductAge(6);
            customMsg.setType(productType);
            customMsg.setRepairSite(Arrays.asList("北京","上海","南京"));
            VariableMap variables = Variables.createVariables();
            variables.put("customMsg", JsonUtil.asString(customMsg));
            externalTaskService.complete(externalTask, variables);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "post_repair_topic", processDefinitionKeyIn = {"Process_spinjson_test"}, lockDuration = 50000)
    public ExternalTaskHandler postRepair() {
        return (externalTask, externalTaskService) -> {
            log.info("任务邮寄维修");
            String customMsgStr = externalTask.getVariable("customMsg");
            JsonObject product = JsonUtil.asObject(customMsgStr);
            log.info(product.toString());
            externalTaskService.complete(externalTask);
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
