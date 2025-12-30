package com.LMS_System.LMS.controller;


import com.LMS_System.LMS.dto.GetUserResponseDto;
import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, GetUserResponseDto>> userprofile(Authentication authentication ){
        GetUserResponseDto responseDto=userService.userProfile(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("profile:",responseDto));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<GetUserResponseDto>> getAllUsers(){

            return ResponseEntity.ok(userService.getlAllUsers());

    }






}
