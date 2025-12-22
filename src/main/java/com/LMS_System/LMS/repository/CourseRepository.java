package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course,Integer> {


    List<Course> findByAuthor(String author);

    boolean existsByAuthor(String author);

    boolean existsByName(String name);

    void deleteByName(String name);
}
