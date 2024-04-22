package me.shijunjiee.camundastudyexternal.external.service;

import com.alibaba.fastjson.JSON;
import io.micrometer.common.util.StringUtils;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class BaseExternalTaskHandler implements ExternalTaskHandler  {
    private ExternalTask externalTask;
    private ExternalTaskService externalTaskService;
    private VariableMap variables;
    private String responseJSON;

    private Logger logger = LoggerFactory.getLogger(BaseExternalTaskHandler.class);

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        this.externalTask = externalTask;
        this.externalTaskService = externalTaskService;
        mappingIn();
        responseJSON = handleExecute();
        mappingOut(responseJSON);
        externalTaskService.complete(externalTask, variables);
    }

    protected abstract String handleExecute();

    private void mappingIn() {
        logger.info("mappingIn");
    }

    private void mappingOut(String responseJSON) {
        logger.info("mappingOut");
        if (StringUtils.isBlank(responseJSON)) {
            return;
        }
        Map<String, Object> responseMap = JSON.parseObject(responseJSON, Map.class);
        logger.info("mappingOut map:{}", responseMap);
    }

    protected Map<String, Object> getAllVariables() {
        return externalTask.getAllVariables();
    }

    protected <T> T getVariable(String variableName) {
        return externalTask.getVariable(variableName);
    }

    protected void setVariable(VariableMap variables) {
        this.variables = variables;
    }

    protected Map<String, String>  getExtensionProperties() {
        return externalTask.getExtensionProperties();
    }
}
