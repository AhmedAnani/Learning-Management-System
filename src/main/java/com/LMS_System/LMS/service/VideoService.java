package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.video.AddVideoDto;
import com.LMS_System.LMS.dto.video.GetVideoDto;
import com.LMS_System.LMS.dto.video.VideoResponseDto;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Content;
import com.LMS_System.LMS.model.Video;
import com.LMS_System.LMS.repository.ContentRepository;
import com.LMS_System.LMS.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ContentRepository contentRepository;

    // 1. Add video
    public ResponseDto addVideo(AddVideoDto addVideoDto){

        Content content=contentRepository.findById(addVideoDto.getContentId()).orElseThrow(()->new NotFound("Content not fond"));

        Video video= new Video();
        video.setPath(addVideoDto.getPath());
        video.setName(addVideoDto.getName());
        video.setCreationTime(addVideoDto.getCreationTime());
        video.setContent(content);
        videoRepository.save(video);

        return new ResponseDto("Video saved successfully.");
    }

    // 2. Get video
    public VideoResponseDto getVideo(GetVideoDto getVideoDto){
        Video video=videoRepository.findById(getVideoDto.getVideoId()).orElseThrow(()->new NotFound("Video not fond"));


        return new VideoResponseDto(video.getId(),
                video.getName(),
                video.getPath());
    }

    // 3. Delete video
    public ResponseDto deleteVideo(GetVideoDto getVideoDto){
        Video video=videoRepository.findById(getVideoDto.getVideoId()).orElseThrow(()->new NotFound("Video not fond"));

        videoRepository.delete(video);
        return new ResponseDto("Video deleted successfully.");

    }
}
