package com.test.berlin_clock.controller;

import com.test.berlin_clock.exception.IncorrectBerlinClockTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<Object> handleIncorrectDigitalTime(DateTimeParseException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = IncorrectBerlinClockTimeException.class)
    public ResponseEntity<Object> handleIncorrectBerlinClockTime(IncorrectBerlinClockTimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }
}
