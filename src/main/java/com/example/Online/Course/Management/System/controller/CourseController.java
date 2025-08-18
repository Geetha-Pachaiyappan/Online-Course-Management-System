package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.CourseRequestDto;
import com.example.Online.Course.Management.System.dto.CourseResponseDto;
import com.example.Online.Course.Management.System.entity.Course;
import com.example.Online.Course.Management.System.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<List<CourseResponseDto>> saveCourses(@RequestBody List<CourseRequestDto> courseRequestDtos){
        return new ResponseEntity<>(courseService.addCourses(courseRequestDtos), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDto>> getAllCourse(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "courseId") String courseId){
        return new ResponseEntity<>(courseService.getAllCourses(page,size,courseId),HttpStatus.OK);
    }

}
