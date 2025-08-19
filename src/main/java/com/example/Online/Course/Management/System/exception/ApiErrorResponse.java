package com.example.Online.Course.Management.System.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime timeStamp;
    private String message;
    private int statusCode;
    private String path;
}
