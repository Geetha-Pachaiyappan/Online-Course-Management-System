package com.example.Online.Course.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {
    private long phoneNumber;
    private String address;
  //  private byte[] profilePicture;
    private Date createdDate;
    private Date updatedDate;
}
