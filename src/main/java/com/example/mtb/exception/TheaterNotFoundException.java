package com.example.mtb.exception;

import lombok.Getter;

@Getter
public class TheaterNotFoundException extends  RuntimeException{



    private  String message;

    public TheaterNotFoundException(String message) {
        this.message = message;
    }

}
