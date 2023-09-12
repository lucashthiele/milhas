package com.lucashthiele.milhas.infra.exception;

import com.lucashthiele.milhas.exceptions.DestinationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<ValidationErrorDTO> handleDestinationNotFound(DestinationNotFoundException err){
        var error = new ValidationErrorDTO(err.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
