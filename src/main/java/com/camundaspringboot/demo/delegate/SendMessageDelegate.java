package com.camundaspringboot.demo.delegate;

import com.camundaspringboot.demo.service.KafkaService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class SendMessageDelegate implements JavaDelegate {
    private final Logger logger = getLogger(this.getClass());
    @Autowired
    KafkaService kafkaService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String taskName = delegateExecution.getCurrentActivityName();
        logger.info("Service task {} is executed:", taskName);
        String itemVariable = (String) delegateExecution.getVariable("item");
        kafkaService.sendMessage(itemVariable);
        logger.info("Service task {} is done:", taskName);
    }
}
