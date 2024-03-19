package me.shijunjiee.camundastudyexternal.config;

import feign.auth.BasicAuthRequestInterceptor;
import jakarta.annotation.Resource;
import org.camunda.bpm.client.spring.boot.starter.ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaFeignAuthConfig {

    @Resource
    private ClientProperties clientProperties;

    @Bean
    public BasicAuthRequestInterceptor camundaAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(clientProperties.getBasicAuth().getUsername(), clientProperties.getBasicAuth().getPassword());
    }

}
