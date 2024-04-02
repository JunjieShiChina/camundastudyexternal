package me.shijunjiee.camundastudyexternal;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CamundastudyexternalApplication.class)
@Slf4j
public class ExternalFetchTest {

    @Resource
    private ExternalTaskService externalTaskService;

    @Test
    public void testFetchAndLock() {
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
