package me.shijunjiee.camundastudyexternal;

import org.camunda.community.rest.EnableCamundaRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 远程API是通过kotlin实现的，注解有红色报警忽略，可以正常启动就行
@EnableCamundaRestClient
public class CamundastudyexternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundastudyexternalApplication.class, args);
	}

}
