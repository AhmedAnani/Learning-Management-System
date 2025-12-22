package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.AddCourseDto;
import com.LMS_System.LMS.DTO.DeleteCourseDto;
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
    public ResponseEntity<Map<?,?>> getAllCourses(){
        try {
            return courseService.getAllCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/my")
    public ResponseEntity<Map<?,?>> getCoursesByAuthorName(Authentication authentication){
        try {
           return courseService.getmycourses(authentication.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> addCourse(@RequestBody AddCourseDto addCourseDto){
        try {
            return courseService.addCourse(addCourseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> deleteCourse(@RequestBody DeleteCourseDto deleteCourseDto){
        try {
            return courseService.deleteCourse(deleteCourseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
