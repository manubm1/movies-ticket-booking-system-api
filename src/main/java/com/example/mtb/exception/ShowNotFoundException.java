package com.example.mtb.exception;

import lombok.Getter;

@Getter
public class ShowNotFoundException extends RuntimeException{

    private String  message;

    public ShowNotFoundException(String message) {
        this.message = message;
    }
}
