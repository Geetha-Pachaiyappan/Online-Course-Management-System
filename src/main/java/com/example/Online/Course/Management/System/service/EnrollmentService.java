package com.example.Online.Course.Management.System.service;


import com.example.Online.Course.Management.System.dto.EnrollmentRequestDto;
import com.example.Online.Course.Management.System.dto.EnrollmentResponseDto;
import com.example.Online.Course.Management.System.entity.Course;
import com.example.Online.Course.Management.System.entity.Enrollment;
import com.example.Online.Course.Management.System.entity.User;
import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import com.example.Online.Course.Management.System.enums.Roles;
import com.example.Online.Course.Management.System.exception.DuplicateResourceException;
import com.example.Online.Course.Management.System.exception.InsufficientBalanceException;
import com.example.Online.Course.Management.System.exception.ResourceNotFoundException;
import com.example.Online.Course.Management.System.exception.SeatsNotAvailableException;
import com.example.Online.Course.Management.System.repository.CourseRepository;
import com.example.Online.Course.Management.System.repository.EnrollmentRepository;
import com.example.Online.Course.Management.System.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public EnrollmentResponseDto saveAllEnrollments(EnrollmentRequestDto enrollmentRequestDto){
        // Course Entity
        Course course = courseRepo.findById(enrollmentRequestDto.getCourseId())
                .orElseThrow(()-> new ResourceNotFoundException("Course id not found "+ enrollmentRequestDto.getCourseId()));
        // User Entity
        User user = userRepo.findById(enrollmentRequestDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User id is not found "+ enrollmentRequestDto.getUserId()));
        // call method
        isUserAlreadyEnrolled(user,course);
        if(checkAvailableSeats(course) && checkWallet(user,course)){
            // decrease the available seats in course and save
            course.setAvailableSeats(course.getAvailableSeats()-1);
            courseRepo.save(course);
            // decrease money in thw wallet and save user wallet;
            user.setWallet(user.getWallet() - course.getPrice());
        }
        // Save Enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setStatus(String.valueOf(EnrollmentStatus.ACTIVE));
        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);

        return modelMapper.map(savedEnrollment, EnrollmentResponseDto.class);
    }

    public void isUserAlreadyEnrolled(User user, Course course){
        if(enrollmentRepo.checkUserIdAndCourseId(user.getUserId(), course.getCourseId()) != null)
            throw new DuplicateResourceException("This user already enrolled " + course.getTitle() + " Course");
    }
    //Check Available Seats
    public boolean checkAvailableSeats(Course course){
        if(course.getAvailableSeats() <= 0)
            throw new SeatsNotAvailableException("Seats not available ");
        return true;
    }
    //Check Wallet
    public boolean checkWallet(User user, Course course){
        if(user.getWallet() < course.getPrice())
            throw new InsufficientBalanceException("Insufficient balance in your wallet");
        return true;
    }

    public Page<EnrollmentResponseDto> getAllEnrollments(int page, int size, String sortBy ){
//        List<Enrollment> enrollmentList = enrollmentRepo.findAll();
//        return enrollmentList.stream()
//                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDto.class))
//                .toList();
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
        Page<Enrollment> enrollments = enrollmentRepo.findAll(pageable);
        return enrollments.map(enrollment -> {
            EnrollmentResponseDto enrollmentResponseDto ;
            enrollmentResponseDto = modelMapper.map(enrollment,EnrollmentResponseDto.class);
            enrollmentResponseDto.setCourseTitle(enrollment.getCourse().getTitle());
            enrollmentResponseDto.setUserName(enrollment.getUser().getName());
            return enrollmentResponseDto;
        });
    }

    public List<EnrollmentResponseDto> getAllEnrollmentsByUserId(int userId){
        List<Enrollment> enrollments = enrollmentRepo.findByUserUserId(userId);
        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentResponseDto.class))
                .toList();
    }

    public List<EnrollmentResponseDto> getAllEnrollmentsByCourseId(int courseId){
        List<Enrollment> enrollments = enrollmentRepo.findByCourseCourseId(courseId);
        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentResponseDto.class))
                .toList();
    }

    public ResponseEntity<?> getCountOfEnrollments(int courseId){
        Map<String, Object> output = new HashMap<>();
        Integer count =  enrollmentRepo.countOfEnrollmentsByCourse(courseId);
        Course course = courseRepo.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course id not found "+ courseId));
        output.put("Count", count);
        output.put("CourseId", courseId);
        output.put("CourseName", course.getTitle());
        return ResponseEntity.ok().body(output);
    }

    public ResponseEntity<EnrollmentResponseDto>  updateEnrollmentStatus(int enrollmentId, String status){
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(
                ()-> new ResourceNotFoundException("Enrollment id is not found "+ enrollmentId)
        );
        enrollment.setStatus(String.valueOf(EnrollmentStatus.valueOf(status.toUpperCase())));
        enrollmentRepo.save(enrollment);
        return ResponseEntity.ok().body(modelMapper.map(enrollment,EnrollmentResponseDto.class));
    }
}
