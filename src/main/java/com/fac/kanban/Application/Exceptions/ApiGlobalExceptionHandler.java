package com.fac.kanban.Application.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiGlobalExceptionHandler {
    //Valida exceções customizadas da API

    @ExceptionHandler(ApiCustomException.class)
    public ResponseEntity<Object> handleCustomException(ApiCustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(ex.getMessage(), ex.getStatus().value()));
    }

    static class ErrorResponse {
        private final String message;
        private final int statusCode;

        public ErrorResponse(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}