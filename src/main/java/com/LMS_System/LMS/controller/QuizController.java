package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.quiz.AddQuizDto;
import com.LMS_System.LMS.dto.quiz.DeleteQuizDto;
import com.LMS_System.LMS.dto.quiz.GetQuizDto;
import com.LMS_System.LMS.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Map<String,String>> addQuiz(@RequestBody AddQuizDto addQuizDto){
        return quizService.addQuiz(addQuizDto);
    }

    @GetMapping
    private List<?> getQuiz(@RequestBody GetQuizDto getQuizDto){
        return quizService.getQuiz(getQuizDto);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> deleteQuiz(@RequestBody DeleteQuizDto deleteQuizDto){
        return quizService.deleteQuiz(deleteQuizDto);
    }
}
