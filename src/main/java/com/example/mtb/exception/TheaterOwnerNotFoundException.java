package com.example.mtb.exception;

import lombok.Getter;

@Getter
public class TheaterOwnerNotFoundException extends RuntimeException{

    private  String message;

    public TheaterOwnerNotFoundException(String message) {
        this.message = message;
    }
}
