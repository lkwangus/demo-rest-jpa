package org.lkwangus.demo.restjpa.backend.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.lkwangus.demo.restjpa.backend.config.RabbitConfig;
import org.lkwangus.demo.restjpa.backend.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitMQService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @NonNull
    AppUtils appUtils;

    @NonNull
    RabbitConfig rabbitConfig;

    @NonNull
    private AmqpTemplate rabbitTemplate;

    public void send(Object context) {
        logger.info("[send] context:" + appUtils.objectToJsonString(context));
        this.rabbitTemplate.convertAndSend(rabbitConfig.QUEUE_NAME, appUtils.objectToJsonString(context));
    }

    public void get() {
        Object messages = this.rabbitTemplate.receiveAndConvert(rabbitConfig.QUEUE_NAME);
        logger.info("[get] message: " + appUtils.objectToJsonString(messages));
    }
}
