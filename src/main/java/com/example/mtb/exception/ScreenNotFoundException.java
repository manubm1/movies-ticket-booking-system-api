package com.example.mtb.exception;

import lombok.Getter;

@Getter
public class ScreenNotFoundException extends RuntimeException{

    private String message;

    public ScreenNotFoundException(String message) {
        this.message = message;
    }
}
