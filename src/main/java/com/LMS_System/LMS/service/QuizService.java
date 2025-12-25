package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.quiz.AddQuizDto;
import com.LMS_System.LMS.dto.quiz.DeleteQuizDto;
import com.LMS_System.LMS.dto.quiz.GetQuizDto;
import com.LMS_System.LMS.dto.video.DeleteVideoDto;
import com.LMS_System.LMS.model.Content;
import com.LMS_System.LMS.model.Quiz;
import com.LMS_System.LMS.model.Video;
import com.LMS_System.LMS.repository.ContentRepository;
import com.LMS_System.LMS.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ContentRepository contentRepository;

    // 1. Add Quiz
    public ResponseEntity<Map<String,String>> addQuiz(AddQuizDto addQuizDto){
        Content content=contentRepository.findById(addQuizDto.getContentId()).orElse(null);

        if(content==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Content not found"));
        }
        Quiz quiz=new Quiz();
        quiz.setName(addQuizDto.getName());
        quiz.setQuestions(addQuizDto.getQuestions());
        quiz.setCreationTime(addQuizDto.getCreationTime());
        quiz.setContent(content);
        quizRepository.save(quiz);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Quiz saved successfully"));
    }


    // 2. Get quiz
    public List<?> getQuiz(GetQuizDto getQuizDto){
        Quiz quiz=quizRepository.findById(getQuizDto.getId()).orElse(null);
        if(quiz==null){
            return List.of(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message"
                            ,"Quiz not fond")));
        }

        return List.of(quiz);
    }
    // 3. Delete quiz
    public ResponseEntity<Map<String,String>> deleteQuiz(DeleteQuizDto deleteQuizDto){
        Quiz quiz=quizRepository.findById(deleteQuizDto.getQuizId()).orElse(null);
        if (quiz==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Quiz not found."));
        }
        quizRepository.save(quiz);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Quiz deleted successfully."));

    }
}
