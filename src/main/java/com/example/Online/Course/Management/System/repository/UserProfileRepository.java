package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
}
