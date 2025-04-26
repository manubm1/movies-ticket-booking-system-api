package com.example.mtb.responseBuilders;

import com.example.mtb.exception.UserRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AplpicationHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerUserRegistration(UserRegistrationException ex){
      ErrorStructure<String> error = new ErrorStructure<>();
        error.setErrorCode(HttpStatus.FOUND.value());
        error.setErrorMessage(ex.getMessage());
        error.setError(" User already exist");

        return new ResponseEntity<ErrorStructure<String>>(error,HttpStatus.FOUND);
    }
}
