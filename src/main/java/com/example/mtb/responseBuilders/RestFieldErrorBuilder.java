package com.example.mtb.responseBuilders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestFieldErrorBuilder {

    public <T> ResponseEntity<FieldErrorStructure<T>> failFieldError(HttpStatus status, String message, T errors){
        FieldErrorStructure<T> fieldErrorStructure= FieldErrorStructure
                .<T>builder()
                .statusCode(status.value())
                .message(message)
                .error(errors)
                .build();
        return new ResponseEntity<>(fieldErrorStructure, status);
    }
}
