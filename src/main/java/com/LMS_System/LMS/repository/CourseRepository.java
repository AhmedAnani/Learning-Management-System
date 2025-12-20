package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Set<Course> findByUsersEmail(String Email);
}
