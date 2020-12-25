package com.camundaspringboot.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;
@Component
public class SendMessageDelegate implements JavaDelegate {
    private final Logger logger = getLogger(this.getClass());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String taskName=delegateExecution.getCurrentActivityName();
        String name= delegateExecution.getBpmnModelElementInstance().getName();
        logger.info("Service task {} is executed:", taskName);
        String itemVariable = (String) delegateExecution.getVariable("item");
        JavaKafkaProducer.sendMessage(itemVariable);
        logger.info("Service task {} is done:", taskName);
    }
}
