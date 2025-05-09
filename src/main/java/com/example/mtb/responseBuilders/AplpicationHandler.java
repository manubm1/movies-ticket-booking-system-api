package com.example.mtb.responseBuilders;

import com.example.mtb.exception.*;
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

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerUserNotFound(UserNotFoundException ex){
        ErrorStructure<String> error = new ErrorStructure<>();
        error.setErrorCode(HttpStatus.FOUND.value());
        error.setErrorMessage(ex.getMessage());
        error.setError(" User not exist");

        return new ResponseEntity<ErrorStructure<String>>(error,HttpStatus.FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerTheaterOwnerNotFound(TheaterOwnerNotFoundException ex){
        ErrorStructure<String> error = new ErrorStructure<>();
        error.setErrorCode(HttpStatus.FOUND.value());
        error.setErrorMessage(ex.getMessage());
        error.setError(" Theater owner not exist");

        return new ResponseEntity<ErrorStructure<String>>(error,HttpStatus.FOUND);


    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerTheaterNotFound(TheaterNotFoundException ex){
        ErrorStructure<String> error = new ErrorStructure<>();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        error.setError(" Theater Not  exist");

        return new ResponseEntity<ErrorStructure<String>>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerScreenNotFound(ScreenNotFoundException ex){
        ErrorStructure<String> error = new ErrorStructure<>();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        error.setError(" Screen Not  exist");

        return new ResponseEntity<ErrorStructure<String>>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerShowNotFound(ShowNotFoundException ex){
        ErrorStructure<String> error = new ErrorStructure<>();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        error.setError("Already show is fixed for thi time interval");

        return new ResponseEntity<ErrorStructure<String>>(error,HttpStatus.NOT_FOUND);

    }


}
