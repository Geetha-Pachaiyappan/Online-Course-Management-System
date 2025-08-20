package com.example.Online.Course.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiValidationErrorResponse methodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request
    ){
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();
        ApiValidationErrorResponse error = new ApiValidationErrorResponse(
                LocalDateTime.now(),
                errors,
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );
        return error;
    }

    @ExceptionHandler(MethodValidationException.class)
    public ApiValidationErrorResponse methodValidationException(
            MethodValidationException exception,
            WebRequest request
    ){
        List<String> errors = exception.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return new ApiValidationErrorResponse(
                LocalDateTime.now(),
                errors,
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ApiValidationErrorResponse handleHandlerMethodValidationException(
            HandlerMethodValidationException exception,
            WebRequest request) {

        // Extract error messages
        List<String> errors = exception.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiValidationErrorResponse errorResponse = new ApiValidationErrorResponse(
                LocalDateTime.now(),
                errors,
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );

        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleExceptions(
            Exception exception,
            WebRequest request
    ){
        return new ApiErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false)
        );
    }
}
