package com.LMS_System.LMS.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public ResponseEntity<Map<String,String>> profile(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("message","You are authenticated with JWT"));
    }
}
