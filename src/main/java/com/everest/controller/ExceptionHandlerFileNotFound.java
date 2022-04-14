package com.everest.controller;

import com.everest.exception.NotFoundFileException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerFileNotFound extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundFileException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request){
        String bodyResp = "File not found!";
        return handleExceptionInternal(ex, bodyResp, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
