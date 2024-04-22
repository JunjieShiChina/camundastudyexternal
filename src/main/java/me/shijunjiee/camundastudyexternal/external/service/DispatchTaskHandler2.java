package me.shijunjiee.camundastudyexternal.external.service;

import com.alibaba.fastjson.JSON;
import me.shijunjiee.camundastudyexternal.dto.TestDTO;
import me.shijunjiee.camundastudyexternal.dto.UserInfoResponse;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ExternalTaskSubscription(topicName = "dispatchTask2", processDefinitionKeyIn = {"Process_sync2"}
        , includeExtensionProperties = true, lockDuration = 20000)
public class DispatchTaskHandler2 extends BaseExternalTaskHandler {

    @Override
    protected String handleExecute() {
        System.out.println("Dispatch task handler");
        Map<String, Object> allVariables = getAllVariables();
        allVariables.forEach((k, v) -> System.out.println(k + " : " + v));

        // 获取扩展属性
        Map<String, String> extensionProperties = getExtensionProperties();
        extensionProperties.forEach((k, v) -> System.out.println(k + " : " + v));

        VariableMap variableMap = Variables.createVariables();
        variableMap.put("dispatchTaskHandler2", "dispatchTaskHandler2");
        variableMap.put("dispatchTaskHandler2_2", "dispatchTaskHandler2_2");
        setVariable(variableMap);

        // mockResponse
        TestDTO testDTO = new TestDTO();
        testDTO.setAge(18);
        testDTO.setName("shijunjiee");
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setSex("男");
        userInfoResponse.setUsername("success");
        testDTO.setUserInfoResponse(userInfoResponse);
        return JSON.toJSONString(testDTO);
    }
}
