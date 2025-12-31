package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.quiz.AddQuizDto;
import com.LMS_System.LMS.dto.quiz.GetQuizDto;
import com.LMS_System.LMS.dto.quiz.QuizResponseDto;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Content;
import com.LMS_System.LMS.model.Quiz;
import com.LMS_System.LMS.repository.ContentRepository;
import com.LMS_System.LMS.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ContentRepository contentRepository;

    // 1. Add Quiz
    public ResponseDto addQuiz(AddQuizDto addQuizDto){
        Content content=contentRepository.findById(addQuizDto.getContentId()).orElseThrow(()->new NotFound("Content not found"));

        Quiz quiz=new Quiz();
        quiz.setName(addQuizDto.getName());
        quiz.setQuestions(addQuizDto.getQuestions());
        quiz.setCreationTime(addQuizDto.getCreationTime());
        quiz.setContent(content);
        quizRepository.save(quiz);

        return new ResponseDto("Quiz saved successfully");
    }


    // 2. Get quiz
    public QuizResponseDto getQuiz(GetQuizDto getQuizDto){
        Quiz quiz=quizRepository.findById(getQuizDto.getId()).orElseThrow(()->new NotFound("Quiz not fond"));


        return new QuizResponseDto(quiz.getId(),
                quiz.getName(),
                quiz.getQuestions(),
                quiz.getCreationTime());
    }
    // 3. Delete quiz
    public ResponseDto deleteQuiz(GetQuizDto getQuizDto){
        Quiz quiz=quizRepository.findById(getQuizDto.getId()).orElseThrow(()->new NotFound("Quiz not fond"));

        quizRepository.save(quiz);
        return new ResponseDto("Quiz deleted successfully.");

    }
}
