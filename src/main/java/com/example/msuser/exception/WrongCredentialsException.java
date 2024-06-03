package com.example.msuser.exception;

import lombok.Getter;

@Getter
public class WrongCredentialsException extends RuntimeException {
    private String code;

    public WrongCredentialsException(String message, String code) {
        super(message);
        this.code = code;
    }
}