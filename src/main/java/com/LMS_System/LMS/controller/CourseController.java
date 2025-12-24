package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.course.AddCourseDto;
import com.LMS_System.LMS.dto.course.DelteCourseDto;
import com.LMS_System.LMS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Map<?, ?>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/my")
    public ResponseEntity<Map<String, ?>> getCoursesByAuthorName(Authentication authentication) {
        if(authentication!=null){return courseService.getMyCourses(authentication.getName());}
        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message","asdfasfs"));

    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addCourse(
            @RequestBody AddCourseDto addCourseDto,Authentication authentication
    ) {
        return courseService.addCourse(addCourseDto,authentication.getName()  );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteCourse(
            @RequestBody DelteCourseDto delteCourseDto) {
        return courseService.deleteCourse(delteCourseDto.getCourseName(),delteCourseDto.getAuthor());
    }
}
