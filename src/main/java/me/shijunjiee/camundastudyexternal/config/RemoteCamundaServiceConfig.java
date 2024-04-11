package me.shijunjiee.camundastudyexternal.config;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteCamundaServiceConfig {

    @Bean(name = "runtimeService")
    public RuntimeService createRumtimeService(@Qualifier("remote") RuntimeService runtimeService) {
        return runtimeService;
    }

    @Bean(name = "repositoryService")
    public RepositoryService createRepositoryService(@Qualifier("remote") RepositoryService repositoryService) {
        return repositoryService;
    }

    @Bean(name = "historyService")
    public HistoryService createHistoryService(@Qualifier("remote") HistoryService historyService) {
        return historyService;
    }

    @Bean(name = "taskService")
    public TaskService createTaskService(@Qualifier("remote") TaskService taskService) {
        return taskService;
    }

}
