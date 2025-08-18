package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseModuleRepository extends JpaRepository<Modules,Integer> {
}
