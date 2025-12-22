package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.AddCourseDto;
import com.LMS_System.LMS.DTO.DeleteCourseDto;
import com.LMS_System.LMS.DTO.DelteCourseDto;
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
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Map<?, ?>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/my")
    public ResponseEntity<Map<?, ?>> getCoursesByAuthorName(Authentication authentication) {
        return courseService.getMyCourses(authentication.getName());
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
