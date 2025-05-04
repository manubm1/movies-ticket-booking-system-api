package com.example.mtb.exception;

import lombok.Getter;

@Getter
public class MovieNotFoundException extends RuntimeException{
    private String message;

    public MovieNotFoundException(String message) {
        this.message = message;
    }
}

