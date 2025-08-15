package com.example.Online.Course.Management.System.entity;

import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;
    private Date createdDate;
    private Date updatedDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;
}
