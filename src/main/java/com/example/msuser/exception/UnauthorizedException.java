package com.example.msuser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {

    private String code;

    public UnauthorizedException(String message, String code) {
        super(message);
        this.code = code;
    }
}