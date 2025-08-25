package com.example.Online.Course.Management.System.dto;

import java.util.Date;

import com.example.Online.Course.Management.System.entity.UserProfile;
import com.example.Online.Course.Management.System.enums.Roles;

import com.example.Online.Course.Management.System.validations.ValidRole;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private int userId;
    private String name;
    private String email;
    @ValidRole
    private String role;
    private double wallet;
    private Date createdDate;
    private Date updatedDate;
    private UserProfileResponseDto userProfile;
}
