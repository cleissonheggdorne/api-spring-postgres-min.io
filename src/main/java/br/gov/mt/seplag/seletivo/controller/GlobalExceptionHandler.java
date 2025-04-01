package br.gov.mt.seplag.seletivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.gov.mt.seplag.seletivo.tools.CustomException;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleResourceNotFoundException(CustomException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}
