package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.video.AddVideoDto;
import com.LMS_System.LMS.dto.video.DeleteVideoDto;
import com.LMS_System.LMS.dto.video.GetVideoDto;
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
    public ResponseEntity<Map<String,String>>  addVideo(AddVideoDto addVideoDto){

        Content content=contentRepository.findById(addVideoDto.getContentId()).orElse(null);

        if (content==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Content not found."));
        }
        Video video= new Video();
        video.setPath(addVideoDto.getPath());
        video.setName(addVideoDto.getName());
        video.setCreationTime(addVideoDto.getCreationTime());
        video.setContent(content);
        videoRepository.save(video);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Video saved successfully."));
    }

    // 2. Get video
    public List<?> getVideo(GetVideoDto getVideoDto){
        Video video=videoRepository.findById(getVideoDto.getVideoId()).orElse(null);

        if (video==null){
             return List.of(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Video not found.")));
        }

        return List.of(video);
    }

    // 3. Delete video
    public ResponseEntity<Map<String,String>> deleteVideo(DeleteVideoDto deleteVideoDto){
        Video video=videoRepository.findById(deleteVideoDto.getVideoId()).orElse(null);
        if (video==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Video not found."));
        }
        videoRepository.save(video);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Video deleted successfully."));

    }
}
