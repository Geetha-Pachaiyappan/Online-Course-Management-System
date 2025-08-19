package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    // Find all courses by instructor’s id.
 //   @Query("Select c from Course c where c.User = ?1 ")
    List<Course> findByUserUserId(int instructorId);

    // Find courses whose title contains a keyword (case insensitive).
    @Query("select c from Course c " +
            "left join c.categoryList cat " +
            "left join c.modules m " +
            "left join m.lessonsList l " +
            "where Lower(c.title) like lower(concat('%', ?1, '%')) " +
            "or lower(c.description) like lower(concat('%', ?1, '%')) " +
            "or lower(cat.name) like lower(concat('%', ?1, '%')) " +
            "or lower(m.title) like lower(concat('%', ?1, '%')) " +
            "or lower(l.title) like lower(concat('%', ?1, '%')) ")
    List<Course> findByKeyword(String text);

    //Find top 5 most recently created courses.
    //@Query("select c from Course c limit 5") --> jpql does not support limit
   @Query(value =  "select * from course limit 5", nativeQuery = true)
    List<Course> findRecentlyCreatedCourses();
}
