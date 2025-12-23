package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.AddContentDto;
import com.LMS_System.LMS.DTO.GetAllContentDto;
import com.LMS_System.LMS.DTO.GetContentDto;
import com.LMS_System.LMS.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping
    public ResponseEntity<Map<String,String>> addContent(@RequestBody AddContentDto addContentDto){
        return contentService.addContent(addContentDto);
    }

    @GetMapping
    public List<?> getContent(@RequestBody GetContentDto getContentDto){
        return contentService.getContentById(getContentDto);
    }

    @GetMapping("/all")
    public List<?> getAllContent(@RequestBody GetAllContentDto getAllContentDto){
        return contentService.getAllContentBySectionId(getAllContentDto);
    }
}
