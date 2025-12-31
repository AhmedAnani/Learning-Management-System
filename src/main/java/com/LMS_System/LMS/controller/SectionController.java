package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.section.AddSectionDto;
import com.LMS_System.LMS.dto.section.SectionRequestDto;
import com.LMS_System.LMS.dto.section.SectionsRequestDto;
import com.LMS_System.LMS.dto.section.SectionResponseDto;
import com.LMS_System.LMS.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping
    public ResponseEntity<ResponseDto> addSection(@Valid @RequestBody AddSectionDto addSectionDto){

        return ResponseEntity.ok(sectionService.addSection(addSectionDto));
    }

    @GetMapping
    public ResponseEntity<SectionResponseDto> getSection(@Valid @RequestBody SectionRequestDto sectionRequestDto){

        return ResponseEntity.ok(sectionService.getSection(sectionRequestDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectionResponseDto>> getAllSections(@Valid @RequestBody SectionsRequestDto sectionsRequestDto){

        return ResponseEntity.ok(sectionService.getAllSectionsCourse(sectionsRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteSection(@Valid @RequestBody SectionRequestDto sectionRequestDto){

        return ResponseEntity.ok(sectionService.deleteSection(sectionRequestDto));
    }
}
