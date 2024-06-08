package com.example.msuser.service.concrete;

import com.example.msuser.exception.NotFoundException;
import com.example.msuser.exception.WrongCredentialsException;
import com.example.msuser.model.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ExceptionResponse();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return new ExceptionResponse();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(WrongCredentialsException ex) {
        log.error("NotFoundException: ", ex);
        return new ExceptionResponse();
    }
}