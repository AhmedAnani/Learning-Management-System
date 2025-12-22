package com.LMS_System.LMS.controller;


import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map<String,?>> userprofile(Authentication authentication ){
       try {
           return userService.userProfile(authentication.getName());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        try {
            return userService.getlAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}
