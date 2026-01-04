package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.auth.*;
import com.LMS_System.LMS.dto.user.EmailRequestDto;
import com.LMS_System.LMS.service.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@NoArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDto){

           return ResponseEntity.ok(userService.login(loginDto));

    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, ResponseDto>> register(@Valid @RequestBody RegisterDto registerDto){

            ResponseDto registerResponseDto= userService.register(registerDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message",registerResponseDto));

    }
    @PostMapping("/verify")
    public ResponseEntity<Map<String, ResponseDto>> verifyOtp(@Valid @RequestBody VerifyOtpDto verifyOtp){

            ResponseDto responseDto = userService.verifyOtp(verifyOtp);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message",responseDto));
    }
    @PostMapping("/forget-password")
    public ResponseEntity<Map<String,ResponseDto>> forgetPassword(@Valid @RequestBody EmailRequestDto forgetPassword){
       ResponseDto responseDto= userService.forgetPassword(forgetPassword.getEmail());
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message",responseDto));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String,ResponseDto>> resetPassword(@Valid @RequestBody ResetPasswordDto resetPassword){

            ResponseDto responseDto= userService.resetPassword(resetPassword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message",responseDto));
    }

    @DeleteMapping
    public ResponseEntity<Map<String,ResponseDto>> deleteUser(@Valid @RequestBody EmailRequestDto emailRequestDto){
        ResponseDto responseDto= userService.deleteUserByEmail(emailRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message",responseDto));
    }

}
