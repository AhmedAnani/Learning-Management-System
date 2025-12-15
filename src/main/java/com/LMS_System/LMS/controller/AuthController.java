package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.ForgetPasswordDto;
import com.LMS_System.LMS.DTO.ResetPasswordDto;
import com.LMS_System.LMS.DTO.VerifyOtpDto;
import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@NoArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody User user){
        try {
            return service.register(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/verify")
    public ResponseEntity<Map<String,String>> verifyOtp(@RequestBody VerifyOtpDto verifyOtp){
        try {
            return service.verifyOtp(verifyOtp.getEmail(), verifyOtp.getOtp());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/forget-password")
    public ResponseEntity<Map<String,String>> forgetPassword(@RequestBody ForgetPasswordDto forgetPassword){
        try {
            return service.forgetPassword(forgetPassword.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String,String>> resetPassword(@RequestBody ResetPasswordDto resetPassword){
        try {
            return service.resetPassword(resetPassword.getEmail(), resetPassword.getPassword(), resetPassword.getOtp());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
