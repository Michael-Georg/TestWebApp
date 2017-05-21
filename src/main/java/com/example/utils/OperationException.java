package com.example.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class OperationException extends RuntimeException {
    public OperationException(final String message) {
        super(message);
    }
}
