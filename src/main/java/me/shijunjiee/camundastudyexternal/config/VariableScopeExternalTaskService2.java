package me.shijunjiee.camundastudyexternal.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
public class VariableScopeExternalTaskService2 {

    /**
     * 上传一批图片
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "dispatch_tasks", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler dispatchTasks() {
        return (externalTask, externalTaskService) -> {
            log.info("上传一批图片");
            VariableMap variables = Variables.createVariables();
            List<String> pictureList = new ArrayList<>();
            // 模拟上传20张图片
            for (int i=0; i<20; i++) {
                pictureList.add("图片" + i);
            }
            variables.put("pictures", pictureList);
            // 所有图片集合变量范围需要传播，不会涉及到并发安全问题，使用全局变量即可
            externalTaskService.complete(externalTask, variables);
        };
    }

    /**
     * 添加图片名称
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "add_pic_name", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler addPicName() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始添加图片名称-----------");
            String picture = externalTask.getVariable("picture");
            VariableMap variables = Variables.createVariables();
            String picName = "pic_" + picture + ".jpg";
            variables.put("picName", picName);
            log.info("pictureId:{}, picName:{}", picture, picName);
            externalTaskService.complete(externalTask, null, variables);
        };
    }

    /**
     * 去水印
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "water_print_delete", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler waterPrintDelete() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始去水印-----------");
            String picName = externalTask.getVariable("picName");
            String picture = externalTask.getVariable("picture");
            Date date = new Date();
            String dateStr = DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
            log.info("picId:{}, picName:{}, date:{}", picture, picName, dateStr);

            VariableMap variables = Variables.createVariables();
            variables.put("picDate", dateStr);
            externalTaskService.complete(externalTask, null, variables);
        };
    }

    /**
     * 图片删日期
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "pic_date_delete", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler picDateDelete() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始去日期-----------");
            String picName = externalTask.getVariable("picName");
            String picture = externalTask.getVariable("picture");
            String picDate = externalTask.getVariable("picDate");
            String picIdUpperComplete = picture + "_uppder_delete";
            log.info("picId:{}, picName:{}, picDate:{}, picIdUpperComplete:{}", picture, picName, picDate, picIdUpperComplete);

            VariableMap variables = Variables.createVariables();
            variables.put("picIdUpperComplete", picIdUpperComplete);
            externalTaskService.complete(externalTask,null, variables);
        };
    }

    /**
     * 人脸提取
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "face_detect", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler faceDetect() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始人脸提取-----------");
            String picName = externalTask.getVariable("picName");
            String picture = externalTask.getVariable("picture");
            String picFace = picture + "_face";
            log.info("picId:{}, picName:{}, picFace:{}", picture, picName, picFace);

            VariableMap variables = Variables.createVariables();
            variables.put("picFace", picFace);
            externalTaskService.complete(externalTask,null, variables);
        };
    }

    /**
     * 车辆提取
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "car_detect", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler carDetect() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始车辆提取-----------");
            String picName = externalTask.getVariable("picName");
            String picture = externalTask.getVariable("picture");
            String picFace = externalTask.getVariable("picFace");
            String picIdLowerComplete = picture + "_lower_delete";
            log.info("picId:{}, picName:{}, picFace:{}, picIdLowerComplete:{}", picture, picName, picFace, picIdLowerComplete);

            VariableMap variables = Variables.createVariables();
            variables.put("picIdLowerComplete", picIdLowerComplete);
            externalTaskService.complete(externalTask,null, variables);
        };
    }

    /**
     * 上传单张图片
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "upload_single", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler uploadSingle() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始上传单张图片-----------");
            String picName = externalTask.getVariable("picName");
            String picture = externalTask.getVariable("picture");
            log.info("picId:{}, picName:{}", picture, picName);
            externalTaskService.complete(externalTask);
        };
    }

    /**
     * 下载所有图片
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "download_pictures", processDefinitionKeyIn = {"Process_variable_scope"}, lockDuration = 20000)
    public ExternalTaskHandler downloadPictures() {
        return (externalTask, externalTaskService) -> {
            log.info("---------开始下载所有图片-----------");
            String picName = externalTask.getVariable("picName");
            String picture = externalTask.getVariable("picture");
            log.info("picId:{}, picName:{}", picture, picName);
            externalTaskService.complete(externalTask);
        };
    }

}
