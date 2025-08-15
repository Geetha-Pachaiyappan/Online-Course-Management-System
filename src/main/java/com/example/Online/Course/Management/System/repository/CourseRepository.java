package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
