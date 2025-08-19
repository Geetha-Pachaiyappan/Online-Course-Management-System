package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.enums.Roles;
import com.example.Online.Course.Management.System.validations.ValidRole;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotNull
    @Size(min = 3, message = "Name Should be Not Empty")
    private String name;
    @NotNull
    @Email(message = "Email Should be Valid")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long," +
                    " contain one digit, one lowercase, one uppercase, one special character, and no spaces")
    private String password;
    @ValidRole(message = "Invalid Role!!!")
    private String role;
    @PositiveOrZero
    @NotNull
    private double wallet;

}
