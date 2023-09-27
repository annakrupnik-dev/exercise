package com.intuit.exercise.exception;

import com.intuit.exercise.model.ErrorResponseDto;
import com.intuit.exercise.model.enums.ServiceMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.SocketTimeoutException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Call resulted in MethodArgumentNotValidException for the request {}, exception: ", request, ex);
        String validationErrorMessages = "[" +
                ex.getBindingResult().getAllErrors().stream()
                        .map(error -> ((FieldError) error).getField() + " " + error.getDefaultMessage())
                        .collect(Collectors.joining(", "))
                + "]";
        return new ResponseEntity<>(new ErrorResponseDto(BAD_REQUEST, ServiceMessages.INVALID_INPUT, validationErrorMessages), BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("call resulted in not found exception for the request {}, exception: ", request, ex);
        return new ResponseEntity<>(new ErrorResponseDto(NOT_FOUND, ServiceMessages.ACTION_DECLINE, ex.getMessage()),NOT_FOUND);
    }

    @ExceptionHandler(RuntimeApplicationException.class)
    private ResponseEntity<ErrorResponseDto> handleBaseException(RuntimeApplicationException ex) {
        return new ResponseEntity<>(ex.getErrorResponseDto(), NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<Object> handleThrowableErrors(Exception ex, WebRequest request) {
        log.error("Call resulted in error for the request {}, exception:  ", request, ex);
        return new ResponseEntity<>(BAD_REQUEST);
    }

}