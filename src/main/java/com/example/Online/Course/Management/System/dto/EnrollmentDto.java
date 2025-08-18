package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Course;
import com.example.Online.Course.Management.System.entity.User;
import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
    private int enrollmentId;
    private Date createdDate;
    private Date updatedDate;
    private EnrollmentStatus status;
    private int courseId;
    private int userId;
}
