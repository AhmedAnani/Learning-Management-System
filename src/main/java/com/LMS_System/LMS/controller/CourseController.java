package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.course.AddCourseDto;
import com.LMS_System.LMS.dto.course.DeleteCourseDto;
import com.LMS_System.LMS.dto.course.GetCourseDto;
import com.LMS_System.LMS.dto.course.GetCourseResponseDto;
import com.LMS_System.LMS.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<GetCourseResponseDto>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/my")
    public ResponseEntity< List<GetCourseResponseDto>> getCoursesByAuthorName(GetCourseDto getCourseDto) {

        return  ResponseEntity.ok(courseService.getMyCourses(getCourseDto));

    }

    @PostMapping
    public ResponseEntity< ResponseDto> addCourse(
           @Valid @RequestBody AddCourseDto addCourseDto) {

        return ResponseEntity.ok(courseService.addCourse(addCourseDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCourse(
           @Valid @RequestBody DeleteCourseDto deleteCourseDto) {

        return ResponseEntity.ok(courseService.deleteCourse(deleteCourseDto));
    }
}
