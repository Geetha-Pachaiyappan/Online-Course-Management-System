package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
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
    private EnrollmentStatus status;
    private int courseId;
    private int userId;
}
