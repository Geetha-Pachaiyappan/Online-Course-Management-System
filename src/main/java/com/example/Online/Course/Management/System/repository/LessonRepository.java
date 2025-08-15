package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lessons,Integer> {
}
