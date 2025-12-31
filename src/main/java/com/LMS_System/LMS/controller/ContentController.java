package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.content.*;
import com.LMS_System.LMS.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping
    public ResponseEntity< ResponseDto> addContent(@Valid @RequestBody AddContentDto addContentDto){
        return ResponseEntity.ok(contentService.addContent(addContentDto));
    }

    @GetMapping
    public ResponseEntity<ContentResponseDto> getContent(@Valid @RequestBody GetContentDto getContentDto){
        return ResponseEntity.ok(contentService.getContentById(getContentDto));
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ContentResponseDto>> getAllContent(@Valid @RequestBody GetAllContentDto getAllContentDto){
        return ResponseEntity.ok(contentService.getAllContentBySectionId(getAllContentDto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteContent(@Valid @RequestBody GetContentDto getContentDto){

        return ResponseEntity.ok(contentService.deleteContent(getContentDto));
    }
}
