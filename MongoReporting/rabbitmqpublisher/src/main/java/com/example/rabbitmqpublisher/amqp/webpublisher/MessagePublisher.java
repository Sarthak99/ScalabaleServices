package com.example.rabbitmqpublisher.amqp.webpublisher;

import com.example.rabbitmqpublisher.amqp.config.MessagingConfig;
import com.example.rabbitmqpublisher.amqp.dto.RequestOrder;
import com.example.rabbitmqpublisher.amqp.dto.RequestStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/create/report")
    public ResponseEntity<RequestStatus> createReportRequest(){

        ResponseEntity responseEntity = null;
        String requestId = null;

        try {
            requestId = UUID.randomUUID().toString();
            RequestOrder requestOrder = new RequestOrder(requestId,
                                                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                                        ,"ReportCreate");
            RequestStatus requestStatus = new RequestStatus(requestId,"process","Request has been submitted.");
            rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, requestOrder);
            responseEntity = new ResponseEntity(requestStatus, HttpStatus.ACCEPTED);
        } catch(Exception e){
            log.error("Error  at createReportRequest:" + e.getMessage() );
            RequestStatus requestStatus = new RequestStatus(requestId,"failed","Failed to submit request.");
            responseEntity = new ResponseEntity(requestStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
