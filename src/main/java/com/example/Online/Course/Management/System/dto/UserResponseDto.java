package com.example.Online.Course.Management.System.dto;

import java.util.Date;

import com.example.Online.Course.Management.System.entity.UserProfile;
import com.example.Online.Course.Management.System.enums.Roles;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private Roles role;
    private double wallet;
    private Date createdDate;
    private Date updatedDate;
    private UserProfileResponseDto userProfile;
}
