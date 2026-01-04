package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.quiz.AddQuizDto;
import com.LMS_System.LMS.dto.quiz.GetQuizDto;
import com.LMS_System.LMS.dto.quiz.QuizResponseDto;
import com.LMS_System.LMS.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<ResponseDto> addQuiz(@Valid @RequestBody AddQuizDto addQuizDto){

        return ResponseEntity.ok(quizService.addQuiz(addQuizDto));
    }

    @GetMapping
    private ResponseEntity<QuizResponseDto> getQuiz(@Valid @RequestBody GetQuizDto getQuizDto){
        return ResponseEntity.ok(quizService.getQuiz(getQuizDto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteQuiz(@Valid @RequestBody GetQuizDto getQuizDto){

        return ResponseEntity.ok(quizService.deleteQuiz(getQuizDto));
    }
}
