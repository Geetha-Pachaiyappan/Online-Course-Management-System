package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
}
