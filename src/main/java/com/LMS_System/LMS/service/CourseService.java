package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.course.AddCourseDto;
import com.LMS_System.LMS.dto.course.DeleteCourseDto;
import com.LMS_System.LMS.dto.course.GetCourseDto;
import com.LMS_System.LMS.dto.course.GetCourseResponseDto;
import com.LMS_System.LMS.exception.BadRequestException;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Course;
import com.LMS_System.LMS.repository.CourseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
@NoArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // 1. Get all courses in site
    public List<GetCourseResponseDto> getAllCourses(){
         return courseRepository.findAll().stream().map(course -> new GetCourseResponseDto(course.getName()
         ,course.getAuthor()
         ,course.getDescription()
         ,course.getCreationTime()
         ,course.getPrice()
         ,course.getRate()
         ,course.getWatches())).toList();
    }
    // 2. Get my courses
    public List<GetCourseResponseDto> getMyCourses(GetCourseDto getCourseDto){
        if(getCourseDto.getAuthor()==null||getCourseDto.getAuthor().isBlank()){
            throw new BadRequestException("Author must not be empty");
        }

        List<Course> courses =courseRepository.findByAuthor(getCourseDto.getAuthor());
         return courses.stream().map(course -> new GetCourseResponseDto(course.getName(),
                 course.getAuthor(),
                 course.getDescription(),
                 course.getCreationTime(),
                 course.getPrice(),
                 course.getRate(),
                 course.getWatches())).toList();
    }

    // 3. Add course
    public ResponseDto addCourse(
            AddCourseDto addCourseDto) {
        Course course = new Course();
        course.setName(addCourseDto.getName());
        course.setDescription(addCourseDto.getDescription());
        course.setPrice(addCourseDto.getPrice());
        course.setCreationTime(LocalDate.now());
        course.setAuthor(addCourseDto.getAuthor());

        courseRepository.save(course);

        return new ResponseDto( "Course saved successfully");
    }


    // 4. Delete course
    public ResponseDto deleteCourse(DeleteCourseDto deleteCourseDto)
    {
        Course course = courseRepository
                .findByNameAndAuthor(deleteCourseDto.getCourseName(), deleteCourseDto.getAuthorName());

        if (course == null) {
            throw new NotFound( "Course not found or author mismatch");
        }

        courseRepository.delete(course);

        return new ResponseDto("Course deleted successfully");

    }


}
