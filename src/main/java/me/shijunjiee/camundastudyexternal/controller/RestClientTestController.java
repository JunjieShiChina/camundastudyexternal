package me.shijunjiee.camundastudyexternal.controller;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestClientTestController {

    private RuntimeService runtimeService;

    private RepositoryService repositoryService;

    private HistoryService historyService;

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

}
