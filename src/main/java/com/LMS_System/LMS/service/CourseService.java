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

import java.time.LocalDate;
import java.util.Map;


@Service
@NoArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // 1. Get all courses in site
    public ResponseEntity<Map<?,?>> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("courses",courseRepository.findAll()));
    }
    // 2. Get my courses
    public ResponseEntity<Map<?,?>> getmycourses(String author){
        if(author==null||author.isBlank()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Not found"));
        }
         return ResponseEntity.status(HttpStatus.OK).body(Map.of("Your courses",courseRepository.findByAuthor(author)));
    }

    // 3. Add course
    public ResponseEntity<Map<String,String>> addCourse(String name, String author, String description, double price, LocalDate creationTime){
        Course course=new Course();
        course.setName(name);
        course.setAuthor(author);
        course.setPrice(price);
        course.setDescription(description);
        course.setCreationTime(creationTime);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Course name",course.getName(),"message","Course saved successfully"));
    }

    // 4. Delete course
    public ResponseEntity<Map<String, String>> deleteCourse(
            String courseName,
            String author
    ) {
        Course course = courseRepository
                .findByNameAndAuthor(courseName, author);

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Course not found or author mismatch"));
        }

        courseRepository.delete(course);

        return ResponseEntity.ok(
                Map.of("message", "Course deleted successfully")
        );
    }


}
