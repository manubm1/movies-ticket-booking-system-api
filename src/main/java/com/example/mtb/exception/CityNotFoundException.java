package com.example.mtb.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityNotFoundException extends  RuntimeException{

    private String message;

    public CityNotFoundException(String message) {
        this.message = message;
    }
}
