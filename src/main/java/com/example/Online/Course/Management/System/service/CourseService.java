package com.example.Online.Course.Management.System.service;

import com.example.Online.Course.Management.System.dto.CourseRequestDto;
import com.example.Online.Course.Management.System.dto.CourseResponseDto;
import com.example.Online.Course.Management.System.entity.Course;
import com.example.Online.Course.Management.System.exception.ResourceNotFoundException;
import com.example.Online.Course.Management.System.repository.CategoryRepository;
import com.example.Online.Course.Management.System.repository.CourseRepository;
import com.example.Online.Course.Management.System.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private UserRepository userRepo;

    public List<CourseResponseDto> addCourses(List<CourseRequestDto> dtos){
        List<Course> courseList = dtos.stream()
                .map(courseDto -> {
                    Course course1 = modelMapper.map(courseDto, Course.class);
                    course1.setUser(userRepo.findById(courseDto.getUser()).orElseThrow(()-> new ResourceNotFoundException("User id not found "+ courseDto.getUser())));
                    course1.setCategoryList(categoryRepo.findAllById(courseDto.getCategoryIdsList()));
                    return course1;
                }).toList();
        //save courses
        List<Course> savedCourses = courseRepo.saveAll(courseList);

        // convert course -> dto
        return savedCourses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .toList();
    }

    // Applied Pagination and Sorting
    public Page<CourseResponseDto> getAllCourses(int page, int size, String sortBy){
//      List<Course> courses = courseRepo.findAll();
//        return courses.stream()
//                .map(course -> modelMapper.map(course, CourseResponseDto.class))
//                .toList();
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
        Page<Course> courses = courseRepo.findAll(pageable);
        return courses.map(course -> modelMapper.map(course,CourseResponseDto.class));
    }

    public List<CourseResponseDto> findCoursesByInstructorId(int instructorId){
        List<Course> courses = courseRepo.findByUserUserId(instructorId);
        if(courses.isEmpty()){
            throw new ResourceNotFoundException("Instructor id not valid "+ instructorId );
        }
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .toList();
    }

    public List<CourseResponseDto> findByKeyword(String text){
        List<Course> courses = courseRepo.findByKeyword(text);
        return courses.stream()
                .map(course -> modelMapper.map(course,CourseResponseDto.class))
                .toList();
    }

    public List<CourseResponseDto> findByRecentlyCreatedCourses(){
        List<Course> courses = courseRepo.findRecentlyCreatedCourses();
        return courses.stream()
                .map(course -> modelMapper.map(course,CourseResponseDto.class))
                .toList();
    }
}
