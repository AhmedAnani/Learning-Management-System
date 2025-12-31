package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.article.ArticleResponseDto;
import com.LMS_System.LMS.dto.content.*;
import com.LMS_System.LMS.dto.quiz.QuizResponseDto;
import com.LMS_System.LMS.dto.video.VideoResponseDto;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Content;
import com.LMS_System.LMS.model.Section;
import com.LMS_System.LMS.repository.ContentRepository;
import com.LMS_System.LMS.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private SectionRepository sectionRepository;
    public ResponseDto addContent(AddContentDto addContentDto){
        Section section=sectionRepository.findById(addContentDto.getSectionId()).orElse(null);
        if (section==null){
            throw new NotFound("Section not found");
        }
        Content content=new Content();
        content.setName(addContentDto.getName());
        content.setSection(section);
        contentRepository.save(content);
        return new ResponseDto("Saved successfully.");
    }

    public ContentResponseDto getContentById(GetContentDto getContentDto){
            Content content=contentRepository.findById(getContentDto.getContentId()).orElseThrow(()->new NotFound("Content not found"));


        return new ContentResponseDto(content.getId(),
                                        content.getName(),
                                        content.getSection().getId(),
                                        content.getVideos()
                                                .stream()
                                                .map(v -> new VideoResponseDto(
                                                        v.getId(),
                                                        v.getPath(),
                                                        v.getName()
                                                ))
                                                .collect(Collectors.toSet()),
                                        content.getArticles()
                                                .stream()
                                                .map(a -> new ArticleResponseDto(
                                                        a.getId(),
                                                        a.getName(),
                                                        a.getDescription(),
                                                        a.getCreationTime()
                                                ))
                                                .collect(Collectors.toSet()),
                                        content.getQuizzes()
                                                .stream()
                                                .map(q -> new QuizResponseDto(
                                                        q.getId(),
                                                        q.getName(),
                                                        q.getQuestions(),
                                                        q.getCreationTime()
                                                ))
                                                .collect(Collectors.toSet()));

    }

    public Set<ContentResponseDto> getAllContentBySectionId(GetAllContentDto getAllContentDto){
        Section section=sectionRepository.findById(getAllContentDto.getSectionId()).orElseThrow(()->new NotFound("Content not found"));


        return section.getContents()
                .stream()
                .map(content -> new ContentResponseDto(
                        content.getId(),
                        content.getName(),
                        section.getId(),
                        content.getVideos()
                                .stream()
                                .map(v -> new VideoResponseDto(
                                        v.getId(),
                                        v.getPath(),
                                        v.getName()
                                ))
                                .collect(Collectors.toSet()),
                        content.getArticles()
                                .stream()
                                .map(a -> new ArticleResponseDto(
                                        a.getId(),
                                        a.getName(),
                                        a.getDescription(),
                                        a.getCreationTime()
                                ))
                                .collect(Collectors.toSet()),
                        content.getQuizzes()
                                .stream()
                                .map(q -> new QuizResponseDto(
                                        q.getId(),
                                        q.getName(),
                                        q.getQuestions(),
                                        q.getCreationTime()
                                ))
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toSet());
    }

    public ResponseDto deleteContent(GetContentDto getContentDto){

        Content content= contentRepository.findById(getContentDto.getContentId()).orElseThrow(()->new NotFound("Content not found"));

        contentRepository.delete(content);
        return new ResponseDto("Content deleted successfully.");
    }
}
