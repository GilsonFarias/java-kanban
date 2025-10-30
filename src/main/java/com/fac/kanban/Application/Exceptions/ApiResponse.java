package com.fac.kanban.Application.Exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private final HttpStatus status;
    private final String message;
    private final List<T> data;

    public ApiResponse(HttpStatus status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }
}
