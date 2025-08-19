package com.example.Online.Course.Management.System.controller;


import com.example.Online.Course.Management.System.dto.EnrollmentRequestDto;
import com.example.Online.Course.Management.System.dto.EnrollmentResponseDto;
import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import com.example.Online.Course.Management.System.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> saveAllEnrollments(@RequestBody EnrollmentRequestDto enrollmentRequestDtoList){
        return new ResponseEntity<>(enrollmentService.saveAllEnrollments(enrollmentRequestDtoList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EnrollmentResponseDto>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "enrollmentId") String enrollmentId
    ){
        return new ResponseEntity<>(enrollmentService.getAllEnrollments(page,size,enrollmentId), HttpStatus.OK);
    }

    @GetMapping("/user-enrollments/{userId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollmentsByUserId(@PathVariable int userId){
        return ResponseEntity.ok().body(enrollmentService.getAllEnrollmentsByUserId(userId));
    }

    @GetMapping("/course-enrollments/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollmentsByCourseId(@PathVariable int courseId){
       return ResponseEntity.ok().body(enrollmentService.getAllEnrollmentsByCourseId(courseId));
    }

    @GetMapping("/count/{courseId}")
    public ResponseEntity<?> getCountOfEnrollments(@PathVariable int courseId){
        return enrollmentService.getCountOfEnrollments(courseId);
    }

    @PutMapping("/status")
    public ResponseEntity<EnrollmentResponseDto> setEnrollmentStatus(
            @RequestParam int enrollmentId,
            @RequestParam String status){
        return enrollmentService.updateEnrollmentStatus(enrollmentId,status);
    }
}
