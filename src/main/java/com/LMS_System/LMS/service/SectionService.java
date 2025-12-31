package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.content.ContentDto;
import com.LMS_System.LMS.dto.content.ContentResponseDto;
import com.LMS_System.LMS.dto.section.AddSectionDto;
import com.LMS_System.LMS.dto.section.SectionRequestDto;
import com.LMS_System.LMS.dto.section.SectionsRequestDto;
import com.LMS_System.LMS.dto.section.SectionResponseDto;
import com.LMS_System.LMS.exception.NotAcceptable;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Course;
import com.LMS_System.LMS.model.Section;
import com.LMS_System.LMS.repository.CourseRepository;
import com.LMS_System.LMS.repository.SectionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResponseDto addSection(AddSectionDto addSection){
       Course course =courseRepository.findById(addSection.getCourseId()).orElseThrow(()->new NotFound("Course not exist."));

        Section section=new Section();
        section.setName(addSection.getName());
        section.setDescription(addSection.getDescription());
        section.setCourse(course);
        sectionRepository.save(section);
        return new ResponseDto("Section added successfully.");
    }

    public SectionResponseDto getSection(SectionRequestDto sectionRequestDto){
        Section section=sectionRepository.findById(sectionRequestDto.getSectionId()).orElseThrow(()->new NotFound("Section not found."));

        return  new SectionResponseDto(
                section.getId(),
                section.getName(),
                section.getDescription(),
                section.getCourse().getId(),

                section.getContents().stream().map(content -> new ContentDto(
                                content.getId(),
                                content.getName()
                        ))
                        .collect(Collectors.toSet())
        );
    }

    public List<SectionResponseDto> getAllSectionsCourse(SectionsRequestDto getCourseSectionsDto){
            List<Section> sections=sectionRepository.findByCourseId(getCourseSectionsDto.getCourseId());
        return sections.stream().map(section -> new SectionResponseDto(section.getId(),section.getName(),
                                                section.getDescription()
                                                ,section.getCourse().getId()
                                                ,section.getContents().stream().map(c->new ContentDto(c.getId()
                                                ,c.getName())).collect(Collectors.toSet())));
    }

    public ResponseDto deleteSection( SectionRequestDto sectionRequestDto) {

        Section section=sectionRepository.findById(sectionRequestDto.getSectionId()).orElseThrow(()->new NotFound("Section not found."));

        sectionRepository.delete(section);
        return new ResponseDto("Section deleted successfully.");
    }
}
