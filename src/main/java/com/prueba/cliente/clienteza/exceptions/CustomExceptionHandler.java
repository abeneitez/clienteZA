package com.prueba.cliente.clienteza.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prueba.cliente.clienteza.model.Prices;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> priceListCustomException(Exception ex) {
        if(ex.getMessage().contains("brandId")) return new ResponseEntity<>("Parámetro brandId es obligatorio", HttpStatus.NOT_FOUND);
        else if(ex.getMessage().contains("productId")) return new ResponseEntity<>("Parámetro productId es obligatorio", HttpStatus.NOT_FOUND);
        else if(ex.getMessage().contains("Text ''")) return new ResponseEntity<>("Parámetro applicationDate es obligatorio", HttpStatus.NOT_FOUND);
        else return new ResponseEntity<String>("Error validación: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Prices> priceListCustomException (String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
        return new ResponseEntity<Prices>((Prices) map, status);
    }

}
