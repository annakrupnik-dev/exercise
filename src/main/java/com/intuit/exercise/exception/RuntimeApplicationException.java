package com.intuit.exercise.exception;

import com.intuit.exercise.model.ErrorResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RuntimeApplicationException extends RuntimeException {
    protected ErrorResponseDto errorResponseDto;
    public RuntimeApplicationException(ErrorResponseDto response) {
        super();
        this.errorResponseDto = response;
    }
}
