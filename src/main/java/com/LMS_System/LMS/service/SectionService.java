package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.section.AddSectionDto;
import com.LMS_System.LMS.dto.course.GetCourseSectionsDto;
import com.LMS_System.LMS.model.Course;
import com.LMS_System.LMS.model.Section;
import com.LMS_System.LMS.repository.CourseRepository;
import com.LMS_System.LMS.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<Map<String,String>> addSection( AddSectionDto addSection){
       Course course =courseRepository.findById(addSection.getCourseId()).orElse(null);
        if(course== null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Course not exist."));
        }
        Section section=new Section();
        section.setName(addSection.getName());
        section.setDescription(addSection.getDescription());
        section.setCourse(course);
        sectionRepository.save(section);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Section added successfully.",
                                "Course name:", course.getName()));
    }

    public List<?> getSectionOfCourse(GetCourseSectionsDto getCourseSectionsDto){
        List<Section> sections =sectionRepository.findByCourseId(getCourseSectionsDto.getCourseId());
        if(sections==null){
            return List.of("Not found");
        }
        return sectionRepository.findByCourseId(getCourseSectionsDto.getCourseId());
    }
}
