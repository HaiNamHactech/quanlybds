package com.bkap.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiMessage> handlerNotFoundException(NotFoundException ex , WebRequest request){
        return new ResponseEntity<ApiMessage>(
                new ApiMessage(404,ex.getMessage(), LocalDateTime.now())
                ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ApiMessage> handlerDuplicateRecordException(DuplicateRecordException ex , WebRequest request){
        return new ResponseEntity<ApiMessage>(new ApiMessage(400,ex.getMessage(),LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    // xử lý exception chưa được khai báo
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiMessage> handlerException(Exception ex , WebRequest request) {
        return new ResponseEntity<ApiMessage>(
                new ApiMessage(404,ex.getMessage(),LocalDateTime.now()),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ApiMessage(404, ex.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}
