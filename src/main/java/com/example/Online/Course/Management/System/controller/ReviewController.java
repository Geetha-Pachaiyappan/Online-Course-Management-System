package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.ReviewDto;
import com.example.Online.Course.Management.System.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<List<ReviewDto>> saveAllReviews(@RequestBody List<ReviewDto> reviewList){
        return new ResponseEntity<>(reviewService.saveAllReviews(reviewList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/course-review/{courseId}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByCourseId(@PathVariable int courseId){
        return ResponseEntity.ok().body(reviewService.getAllReviewsByCourseId(courseId));
    }

    @GetMapping("/average-rating/{courseId}")
    public ResponseEntity<?> averageOfRating(@PathVariable int courseId){
        return ResponseEntity.ok().body(reviewService.averageRating(courseId));
    }
}
