package com.lucashthiele.milhas.infra.exception;

import com.lucashthiele.milhas.exceptions.DestinationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<ValidationErrorDTO> handleDestinationNotFound(DestinationNotFoundException err){
        var error = new ValidationErrorDTO(err.getMessage());

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDTO>> handleJsonNotValid(MethodArgumentNotValidException err){
        var errors = err.getFieldErrors();
        return ResponseEntity.badRequest().body(errors
                        .stream()
                        .map(filedErrorBuilder())
                        .toList());
    }

    private static Function<FieldError, ValidationErrorDTO> filedErrorBuilder() {
        return (FieldError fieldError) -> new ValidationErrorDTO(fieldError.getField() + ": " + fieldError.getDefaultMessage());
    }
}
