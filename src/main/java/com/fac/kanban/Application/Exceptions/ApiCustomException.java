package com.fac.kanban.Application.Exceptions;

import org.springframework.http.HttpStatus;

public class ApiCustomException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ApiCustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
