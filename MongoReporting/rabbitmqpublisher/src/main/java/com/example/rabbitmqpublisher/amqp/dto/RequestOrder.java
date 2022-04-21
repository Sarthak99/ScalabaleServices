package com.example.rabbitmqpublisher.amqp.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {

    private String requestId;
    private String requestedTime;
    private String requestType;

}
