package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.content.AddContentDto;
import com.LMS_System.LMS.dto.content.DeleteContentDto;
import com.LMS_System.LMS.dto.content.GetAllContentDto;
import com.LMS_System.LMS.dto.content.GetContentDto;
import com.LMS_System.LMS.model.Content;
import com.LMS_System.LMS.model.Section;
import com.LMS_System.LMS.repository.ContentRepository;
import com.LMS_System.LMS.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private SectionRepository sectionRepository;
    public ResponseEntity<Map<String,String>> addContent(AddContentDto addContentDto){
        Section section=sectionRepository.findById(addContentDto.getSectionId()).orElse(null);
        if (section==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Section not found"));
        }
        Content content=new Content();
        content.setName(addContentDto.getName());
        content.setSection(section);
        contentRepository.save(content);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Saved successfully."));
    }

    public List<?> getContentById(GetContentDto getContentDto){
            Content content=contentRepository.findById(getContentDto.getContentId()).orElse(null);
            if(content==null){
                return List.of(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message","Content not found")));
            }

        return List.of(content);

    }

    public List<?> getAllContentBySectionId(GetAllContentDto getAllContentDto){
        Section section=sectionRepository.findById(getAllContentDto.getSectionId()).orElse(null);
        if(section==null){
            return List.of(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Section not found")));
        }

        return List.of(section.getContents());
    }

    public ResponseEntity<Map<String,String>> deleteContent(DeleteContentDto deleteContentDto){

        Content content= contentRepository.findById(deleteContentDto.getContentId()).orElse(null);
        if (content==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Content not found."));
        }
        contentRepository.delete(content);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Content deleted successfully."));
    }
}
