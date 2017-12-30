package com.example.demo.quartz;

import com.example.demo.service.SynWebUeSimilarityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2017/12/11 12:05
 */
@Configuration
@Component
@EnableScheduling
public class ScheduleTask {

    @Resource(name = "synWebUeSimilarityService")
    private SynWebUeSimilarityService synWebUeSimilarityService;

    public void sayHello() {
//        System.out.println("hello world"+System.currentTimeMillis());
        System.out.println("start");
        synWebUeSimilarityService.testJob1();
        synWebUeSimilarityService.testJob2();
        System.out.println("end");
    }

}
