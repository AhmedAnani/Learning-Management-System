package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.AddCourseDto;
import com.LMS_System.LMS.model.Course;
import com.LMS_System.LMS.service.CourseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/courses")
@NoArgsConstructor
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping
    public Set<Course> getAllCoursesByUserEmail(Authentication authentication){
        try {
           return courseService.getcourses(authentication.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> addCourse(@RequestBody AddCourseDto addCourseDto,Authentication authentication){
        try {
            return courseService.addCourse(addCourseDto,authentication.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
