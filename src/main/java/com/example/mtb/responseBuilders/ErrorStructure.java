package com.example.mtb.responseBuilders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorStructure<T> {

     int errorCode;
     String errorMessage;
     T  error;
}
