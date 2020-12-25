package com.camundaspringboot.demo;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component

public class ProcessApp {
    private final Logger logger = getLogger(this.getClass());
    private String processInstanceId;


    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    private void processPostDeploy(PostDeployEvent event) {
        logger.info("========================================");
        logger.info("Successfully started Camunda Showcase");
        logger.info("========================================");
        processInstanceId = runtimeService.startProcessInstanceByKey("payment-retrieval").getProcessInstanceId();
        logger.info("started instance: {}", processInstanceId);
    }
}
