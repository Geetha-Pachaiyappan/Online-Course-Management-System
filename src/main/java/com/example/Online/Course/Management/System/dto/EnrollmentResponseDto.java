package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import com.example.Online.Course.Management.System.validations.ValidEnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDto {
    private int enrollmentId;
    private Date createdDate;
    private Date updatedDate;
    @ValidEnrollmentStatus
    private String status;
//    private int courseId;
//    private int userId;
    private String courseTitle;
    private String userName;
}
