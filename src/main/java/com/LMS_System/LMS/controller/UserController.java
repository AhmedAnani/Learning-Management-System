package com.LMS_System.LMS.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public ResponseEntity<String> profile(){
        return ResponseEntity.ok("You are authenticated with JWT");
    }
}
