package com.example.Online.Course.Management.System.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequestDto {
    @NotNull
    @Size(max = 10)
    private long phoneNumber;
    @NotNull(message = "Address Should not be Null")
    private String address;
    private byte[] profilePicture;
}
