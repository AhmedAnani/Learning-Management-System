package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.video.AddVideoDto;
import com.LMS_System.LMS.dto.video.GetVideoDto;
import com.LMS_System.LMS.dto.video.VideoResponseDto;
import com.LMS_System.LMS.service.VideoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseDto> addVideo(@Valid @RequestBody AddVideoDto addVideoDto){

        return ResponseEntity.ok(videoService.addVideo(addVideoDto));
    }

    @GetMapping
    public ResponseEntity<VideoResponseDto> getVideo(@Valid @RequestBody GetVideoDto getVideoDto){
        return ResponseEntity.ok(videoService.getVideo(getVideoDto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteVideo(@Valid @RequestBody GetVideoDto getVideoDto){
        return ResponseEntity.ok(videoService.deleteVideo(getVideoDto));
    }
}
