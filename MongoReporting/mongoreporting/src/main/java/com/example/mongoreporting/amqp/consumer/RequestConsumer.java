package com.example.mongoreporting.amqp.consumer;

import com.example.mongoreporting.amqp.config.MessagingConfig;
import com.example.mongoreporting.amqp.dto.RequestOrder;
import com.example.mongoreporting.service.DocumentGenerator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestConsumer {

    @Autowired
    private DocumentGenerator documentGenerator;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(RequestOrder requestOrder){
        documentGenerator.generateReport(requestOrder.getRequestId());
        System.out.println("Request consumed - " + requestOrder.getRequestId());
    }
}
