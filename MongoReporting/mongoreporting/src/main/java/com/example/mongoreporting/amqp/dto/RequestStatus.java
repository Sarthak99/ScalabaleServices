package com.example.mongoreporting.amqp.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestStatus {

    private String requestId;
    private String requestStatus;
    private String requestMessage;
}
