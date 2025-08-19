package com.example.Online.Course.Management.System.service;


import com.example.Online.Course.Management.System.dto.EnrollmentDto;
import com.example.Online.Course.Management.System.entity.Course;
import com.example.Online.Course.Management.System.entity.Enrollment;
import com.example.Online.Course.Management.System.repository.CourseRepository;
import com.example.Online.Course.Management.System.repository.EnrollmentRepository;
import com.example.Online.Course.Management.System.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CourseRepository courseRepo;

    public List<EnrollmentDto> saveAllEnrollments(List<EnrollmentDto> enrollmentDto){
        List<Enrollment> enrollmentList = enrollmentDto.stream()
                .map(enrollmentDto1 ->{
                    Enrollment enrollment = modelMapper.map(enrollmentDto1, Enrollment.class);
                    enrollment.setCourse(courseRepo.findById(enrollmentDto1.getCourseId())
                            .orElseThrow(()-> new RuntimeException("Course Id Not Match")));
                    enrollment.setUser(userRepo.findById(enrollmentDto1.getUserId())
                            .orElseThrow(()-> new RuntimeException("User Id Not Match")));
                    return enrollment;
                } ).toList();

        List<Enrollment> savedEnrollments = enrollmentRepo.saveAll(enrollmentList);

        return savedEnrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDto.class))
                .toList();
    }

    public Page<EnrollmentDto> getAllEnrollments(int page, int size, String sortBy ){
//        List<Enrollment> enrollmentList = enrollmentRepo.findAll();
//        return enrollmentList.stream()
//                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDto.class))
//                .toList();
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
        Page<Enrollment> enrollments = enrollmentRepo.findAll(pageable);
        return enrollments.map(enrollment -> modelMapper.map(enrollment,EnrollmentDto.class));
    }

    public List<EnrollmentDto> getAllEnrollmentsByUserId(int userId){
        List<Enrollment> enrollments = enrollmentRepo.findByUserUserId(userId);
        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment,EnrollmentDto.class))
                .toList();
    }

    public List<EnrollmentDto> getAllEnrollmentsByCourseId(int courseId){
        List<Enrollment> enrollments = enrollmentRepo.findByCourseCourseId(courseId);
        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment,EnrollmentDto.class))
                .toList();
    }

    public ResponseEntity<?> getCountOfEnrollments(int courseId){
        Map<String, Object> output = new HashMap<>();
        Integer count =  enrollmentRepo.countOfEnrollmentsByCourse(courseId);
        Course course = courseRepo.findById(courseId)
                .orElseThrow(()-> new RuntimeException("Course id not found"));
        output.put("Count", count);
        output.put("CourseId", courseId);
        output.put("CourseName", course.getTitle());
        return ResponseEntity.ok().body(output);
    }
}
