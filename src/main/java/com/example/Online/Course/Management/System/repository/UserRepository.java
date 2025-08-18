package com.example.Online.Course.Management.System.repository;


import com.example.Online.Course.Management.System.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Course.Management.System.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    //Applying Pagination to List of Users
    //Page<User> findAll(Pageable pageable);

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.role) = LOWER(:role)")
    List<User> findByRole(@Param("role") String role);



}
