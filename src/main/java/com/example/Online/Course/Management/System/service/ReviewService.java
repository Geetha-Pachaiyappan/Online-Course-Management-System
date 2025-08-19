package com.example.Online.Course.Management.System.service;

import com.example.Online.Course.Management.System.dto.ReviewDto;
import com.example.Online.Course.Management.System.entity.Course;
import com.example.Online.Course.Management.System.entity.Review;
import com.example.Online.Course.Management.System.exception.ResourceNotFoundException;
import com.example.Online.Course.Management.System.repository.CourseRepository;
import com.example.Online.Course.Management.System.repository.ReviewRepository;
import com.example.Online.Course.Management.System.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CourseRepository courseRepo;


    public List<ReviewDto> saveAllReviews(List<ReviewDto> reviewDto){
        List<Review> reviewList  = reviewDto.stream()
                .map(reviewDto1 -> {
                    Review review = modelMapper.map(reviewDto1, Review.class);
                    review.setUser(userRepo.findById(reviewDto1.getUserId())
                            .orElseThrow(()-> new ResourceNotFoundException("User id Not Found "+ reviewDto1.getUserId())));
                    review.setCourse(courseRepo.findById(reviewDto1.getCourseId())
                            .orElseThrow(()-> new ResourceNotFoundException("Course id Not Found "+ reviewDto1.getCourseId())));
                    return review;
                }).toList();

        List<Review> savedReviews = reviewRepo.saveAll(reviewList);

        return savedReviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .toList();
    }

    public List<ReviewDto> getAllReviews(){
        List<Review> reviews = reviewRepo.findAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .toList();
    }

    public List<ReviewDto> getAllReviewsByCourseId(int courseId){
        List<Review> reviews = reviewRepo.findAllReviews(courseId);
        return  reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .toList();
    }

    public ResponseEntity<?> averageRating(int courseId){
        Double avgRating = reviewRepo.averageRatingOfCourse(courseId);
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() ->new ResourceNotFoundException("Course id not found "+ courseId));
        Map<String, Object> output = new HashMap<>();
        output.put("CourseId", courseId);
        output.put("CourseName", course.getTitle());
        output.put("Average of Rating", avgRating);
        return ResponseEntity.ok().body(output);
    }
}
