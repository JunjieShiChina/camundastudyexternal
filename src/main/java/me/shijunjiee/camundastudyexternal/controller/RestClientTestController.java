package me.shijunjiee.camundastudyexternal.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.List;

@RestController
@Slf4j
public class RestClientTestController {

    private RuntimeService runtimeService;

    private RepositoryService repositoryService;

    private HistoryService historyService;

    @Resource
    private ExternalTaskService externalTaskService;

    // 远程注入
    public RestClientTestController(@Qualifier("remote") RuntimeService runtimeService,
                                    @Qualifier("remote") RepositoryService repositoryService,
                                    @Qualifier("remote") HistoryService historyService) {
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
        this.historyService = historyService;
    }

    @GetMapping("/test")
    public void test() {
        List<Deployment> list = this.repositoryService.createDeploymentQuery().deploymentId("f0253a7e-d793-11ee-88ec-38d57a8f5f95").list();
        for (Deployment deployment : list) {
            System.out.println(deployment.getId());
        }
    }

    @GetMapping("/test2")
    public void test2() {
        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(50, "external-client1")
                .topic("topictest", 10000L).processDefinitionKey("Process_topic_circle").enableCustomObjectDeserialization()
                .execute();

          for (LockedExternalTask task : tasks) {
              // 当前系统时间，格式yyyy-MM-dd HH:mm:ss
            log.info("topic轮询订阅测试,当前系统时间:{}", DateFormat.getDateTimeInstance().format(System.currentTimeMillis()));
//            externalTaskService.complete(externalTask);
          }
    }

}
