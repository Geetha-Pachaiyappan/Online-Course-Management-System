package com.example.Online.Course.Management.System.controller;


import com.example.Online.Course.Management.System.dto.EnrollmentDto;
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
    public ResponseEntity<List<EnrollmentDto>> saveAllEnrollments(@RequestBody List<EnrollmentDto> enrollmentDtoList){
        return new ResponseEntity<>(enrollmentService.saveAllEnrollments(enrollmentDtoList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EnrollmentDto>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "enrollmentId") String enrollmentId
    ){
        return new ResponseEntity<>(enrollmentService.getAllEnrollments(page,size,enrollmentId), HttpStatus.OK);
    }
}
