package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.AddCourseDto;
import com.LMS_System.LMS.model.Course;
import com.LMS_System.LMS.repository.CourseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@NoArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Set<Course> getcourses(String email){

         return courseRepository.findByUsersEmail(email);
    }

    public ResponseEntity<Map<String,String>> addCourse(AddCourseDto addCourseDto,String author){
        if(addCourseDto==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Add Valid Data"));
        }
        Course course=new Course();
        course.setName(addCourseDto.getName());
        course.setAuthor(author);
        course.setPrice(addCourseDto.getPrice());
        course.setDescription(addCourseDto.getDescription());
        course.setCreation_time(addCourseDto.getCreation_time());
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Course",course.getName(),"message","Course saved successfully"));
    }
}
