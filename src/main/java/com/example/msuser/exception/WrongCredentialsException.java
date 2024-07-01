package com.example.msuser.exception;

import lombok.Getter;

@Getter
public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException(String message) {
        super(message);
    }
}