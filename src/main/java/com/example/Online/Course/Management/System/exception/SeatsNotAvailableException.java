package com.example.Online.Course.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SeatsNotAvailableException extends RuntimeException{
    public SeatsNotAvailableException(String message){
        super(message);
    }
}
