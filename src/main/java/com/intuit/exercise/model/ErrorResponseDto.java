package com.intuit.exercise.model;


import com.intuit.exercise.model.enums.ServiceMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto implements Serializable {

    private Integer id;
    private HttpStatus httpStatusCode;
    private String customerMessage;
    private String developerMessage;


    public ErrorResponseDto(HttpStatus httpStatusCode, ServiceMessages customerMessage, String developerMessage) {
        this.id = customerMessage.getMessageCode();
        this.httpStatusCode = httpStatusCode;
        this.customerMessage = customerMessage.getMessage();
        this.developerMessage = developerMessage;
    }

    public ErrorResponseDto(HttpStatus httpStatusCode, ServiceMessages customerMessage) {
        this.id = customerMessage.getMessageCode();
        this.httpStatusCode = httpStatusCode;
        this.customerMessage = customerMessage.getMessage();
    }
}