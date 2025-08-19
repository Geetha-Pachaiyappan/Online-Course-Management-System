package com.example.Online.Course.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request
    ){
        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false)
        );
        return error;
    }

    @ExceptionHandler(DuplicateResourceException.class)
   public ApiErrorResponse handleDuplicateResourceException(
           DuplicateResourceException ex,
           WebRequest request
   ){
        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );
        return error;
   }

    @ExceptionHandler(SeatsNotAvailableException.class)
    public ApiErrorResponse handleSeatsNotAvailableException(
            SeatsNotAvailableException ex,
            WebRequest request
    ){
        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );
        return error;
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ApiErrorResponse handleInsufficientBalanceException(
            InsufficientBalanceException ex,
            WebRequest request
    ){
        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );
        return error;
    }
}
