package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.section.AddSectionDto;
import com.LMS_System.LMS.dto.section.GetCourseSectionsDto;
import com.LMS_System.LMS.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping
    public ResponseEntity<Map<String,String>> addSection(@RequestBody AddSectionDto addSectionDto){
        return sectionService.addSection(addSectionDto);
    }

    @GetMapping
    public List<?> getCourseSections(@RequestBody GetCourseSectionsDto getCourseSectionsDto){

        return sectionService.getSectionOfCourse(getCourseSectionsDto);
    }
}
