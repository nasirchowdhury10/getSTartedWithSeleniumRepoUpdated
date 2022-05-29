package com.example.demo.advice;

import com.example.demo.exception.InvalidArgmentException;
import com.example.demo.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//@RestControllerAdvice
@Component
public class GlobalExceptionHandlerAdvice {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?>recordNotFoundHandler(RecordNotFoundException e){

        logger.error(e.getMessage(), e);
         return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                 .body(e.getMessage());


    }

    @ExceptionHandler(InvalidArgmentException.class)
    public ResponseEntity<?>badRequestHandler(InvalidArgmentException e){

        logger.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>commonFoundHandler(Exception e){

        logger.error(e.getMessage(), e);
        return ResponseEntity
                .internalServerError()
                .body("user are not man in the bog");


    }
}
