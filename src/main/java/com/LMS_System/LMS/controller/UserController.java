package com.LMS_System.LMS.controller;


import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.user.EmailRequestDto;
import com.LMS_System.LMS.dto.user.GetUserResponseDto;
import com.LMS_System.LMS.service.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity< GetUserResponseDto> userprofile(@Valid @RequestBody EmailRequestDto emailRequestDto){
        GetUserResponseDto responseDto=userService.userProfile(emailRequestDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<GetUserResponseDto>> getAllUsers(){

            return ResponseEntity.ok(userService.getAllUsers());

    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteUser(@Valid @RequestBody EmailRequestDto emailRequestDto){
        return ResponseEntity.ok(userService.deleteUserByEmail(emailRequestDto));
    }





}
