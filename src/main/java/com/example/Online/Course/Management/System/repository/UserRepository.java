package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
