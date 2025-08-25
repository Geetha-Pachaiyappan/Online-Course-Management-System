package com.example.Online.Course.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private int userId;
    private String username;
    private String email;
    private String role;
    private String token;
}
