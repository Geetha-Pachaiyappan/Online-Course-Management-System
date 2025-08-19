package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.enums.Roles;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @Size(min = 6, message = "Password Should be Minimum 6 Characters")
    private String password;
    private Roles role;
    @PositiveOrZero
    @NotNull
    private double wallet;

}
