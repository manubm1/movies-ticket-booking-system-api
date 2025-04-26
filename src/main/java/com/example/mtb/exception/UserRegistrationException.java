package com.example.mtb.exception;

import lombok.Getter;

@Getter
public class UserRegistrationException extends  RuntimeException{

    private String message;


    public UserRegistrationException(String message) {
        this.message = message;
    }
}
