package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.*;
import com.LMS_System.LMS.component.JwtUtil;
import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.repository.UserRepository;
import com.LMS_System.LMS.service.UserService;
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
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
       try {
           return userService.login(loginDto);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody RegisterDto registerDto){
        try {
            return userService.register(registerDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/verify")
    public ResponseEntity<Map<String,String>> verifyOtp(@RequestBody VerifyOtpDto verifyOtp){
        try {
            return userService.verifyOtp(verifyOtp.getEmail(), verifyOtp.getOtp());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/forget-password")
    public ResponseEntity<Map<String,String>> forgetPassword(@RequestBody ForgetPasswordDto forgetPassword){
        try {
            return userService.forgetPassword(forgetPassword.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String,String>> resetPassword(@RequestBody ResetPasswordDto resetPassword){
        try {
            return userService.resetPassword(resetPassword.getEmail(), resetPassword.getPassword(), resetPassword.getOtp());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
