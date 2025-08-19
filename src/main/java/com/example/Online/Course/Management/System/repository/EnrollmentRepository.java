package com.example.Online.Course.Management.System.repository;

import com.example.Online.Course.Management.System.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {

    //Find all enrollments for a given user.
    List<Enrollment> findByUserUserId( int userId);

    //Find all users enrolled in a specific course.
    List<Enrollment> findByCourseCourseId(int courseId);

    //Count how many students enrolled in a course
    @Query("select count(e) from Enrollment e " +
            "join e.course c " +
            "where c.courseId = ?1 ")
    int countOfEnrollmentsByCourse(int courseId);

    @Query("select e from Enrollment e " +
            "join e.course c " +
            "join e.user u " +
            "where c.courseId = ?2 " +
            "AND u.userId = ?1 ")
    Enrollment checkUserIdAndCourseId(int userId, int courseId);

}
