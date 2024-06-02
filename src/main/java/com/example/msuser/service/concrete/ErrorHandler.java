package com.example.msuser.service.concrete;

import com.example.msuser.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public NotFoundException handle(Exception ex) {
        log.error("Exception: ", ex);
        return new NotFoundException("Exception: " + ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public NotFoundException handle(NotFoundException ex) {
        log.error("Exception: ", ex);
        return new NotFoundException("Exception: " + ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public NotFoundException handle(BadRequestException ex) {
        log.error("Exception: ", ex);
        return new NotFoundException("Exception: " + ex.getMessage());
    }
}