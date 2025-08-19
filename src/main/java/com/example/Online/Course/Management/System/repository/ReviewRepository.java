package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    // Find all reviews for a course, sorted by rating (high → low).
    @Query("select r from Review r " +
            "join r.course c " +
            "where c.courseId = ?1 " +
            "order by r.rating desc")
    List<Review> findAllReviews(int courseId);

    // Find the average rating of a course.
    @Query("select avg(r.rating) from Review r " +
            "join r.course c " +
            "where c.courseId = ?1 ")
    Double averageRatingOfCourse(int courseId);



}
