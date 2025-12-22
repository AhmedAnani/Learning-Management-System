package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.AddCourseDto;
import com.LMS_System.LMS.DTO.DeleteCourseDto;
import com.LMS_System.LMS.model.Course;
import com.LMS_System.LMS.repository.CourseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@NoArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses in site
    public ResponseEntity<Map<?,?>> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("courses",courseRepository.findAll()));
    }
    // Get all his courses for the author
    public ResponseEntity<Map<?,?>> getmycourses(String author){
        if(author==null||author.isBlank()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Not found"));
        }
         return ResponseEntity.status(HttpStatus.OK).body(Map.of("Your courses",courseRepository.findByAuthor(author)));
    }

    // Add course
    public ResponseEntity<Map<String,String>> addCourse(AddCourseDto addCourseDto){
        if(addCourseDto==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Add Valid Data"));
        }
        Course course=new Course();
        course.setName(addCourseDto.getName());
        course.setAuthor(addCourseDto.getAuthor());
        course.setPrice(addCourseDto.getPrice());
        course.setDescription(addCourseDto.getDescription());
        course.setCreationTime(addCourseDto.getCreation_time());
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Course name",course.getName(),"message","Course saved successfully"));
    }

    // Delete course
    public ResponseEntity<Map<String,String>> deleteCourse
    (DeleteCourseDto deleteCourseDto){
        if (!courseRepository.existsByName(deleteCourseDto.getCourseName()) ||
                !courseRepository.existsByAuthor(deleteCourseDto.getAuthorName())) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Course not found or author mismatch"));
        }

        courseRepository.deleteByName(deleteCourseDto.getCourseName());

        return ResponseEntity.ok(
                Map.of("message", "Course deleted successfully")
        );
    }
}
