package com.example.Online.Course.Management.System.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiValidationErrorResponse {
    private LocalDateTime timeStamp;
    private List<String> errors;
    private int statusCode;
    private String path;
}
