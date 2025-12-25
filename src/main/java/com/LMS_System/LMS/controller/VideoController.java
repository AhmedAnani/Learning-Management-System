package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.video.AddVideoDto;
import com.LMS_System.LMS.dto.video.GetVideoDto;
import com.LMS_System.LMS.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<Map<String,String>> addVideo(@RequestBody AddVideoDto addVideoDto){
        return videoService.addVideo(addVideoDto);
    }

    @GetMapping
    public List<?> getVideo(@RequestBody GetVideoDto getVideoDto){
        return videoService.getVideo(getVideoDto);
    }
}
