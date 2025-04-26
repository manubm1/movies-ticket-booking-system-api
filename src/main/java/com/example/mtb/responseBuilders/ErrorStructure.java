package com.example.mtb.responseBuilders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorStructure<T> {

    private int errorCode;
    private String errorMessage;
    private T  error;
}
