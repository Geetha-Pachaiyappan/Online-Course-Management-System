package com.example.Online.Course.Management.System.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private String timeStamp;
    private String message;
    private int statusCode;
    private String path;
}
