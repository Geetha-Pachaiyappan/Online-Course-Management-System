package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequestDto {
    private int courseId;
    private int userId;
}
