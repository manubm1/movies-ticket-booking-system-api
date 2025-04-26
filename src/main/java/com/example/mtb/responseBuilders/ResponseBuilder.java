package com.example.mtb.responseBuilders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Component
public class ResponseBuilder {
    public <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data){
        ResponseStructure<T> structure = ResponseStructure.<T> builder()
                .statusCode(status.value())
                .message(message)
                .data(data)
                .build();;

        return  new ResponseEntity<>(structure,status);
    }

//    public <T> ResponseEntity<ErrorStructure> error(HttpStatus status, String message) {
//        ErrorStructure structure = ErrorStructure.builder()
//                .errorCode(status.value())
//                .errorMessage(message)
//                .build();
//
//
//        return new ResponseEntity<>(structure, status);
//    }
}
