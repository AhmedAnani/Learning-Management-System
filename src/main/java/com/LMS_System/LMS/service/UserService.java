package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.LoginDto;
import com.LMS_System.LMS.component.JwtUtil;
import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@Service
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<Map<String,String>> register(User user){
        if(userRepo.findByEmail(user.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message","You already have an account"));
        }
        String otp=generateOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
        userRepo.save(user);
        emailService.sendOtp(user.getEmail(),otp);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message","OTP sent to your email for verification. "));
    }
    @Transactional
    public ResponseEntity<Map<String,String>> verifyOtp(String email,String otp){
        User user=userRepo.findByEmail(email);
        if(user.getOtpExpiration().isBefore(LocalDateTime.now())){
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body(Map.of("message","OTP expired"));
        }
        if(!user.getOtp().equals(otp)){
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Invalid OTP"));
        }
        user.setVerified(true);
        user.setOtp(null);
        userRepo.save(user);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(Map.of("message","Account verified successfully!"));
    }
    @Transactional
    public ResponseEntity<Map<String,String>> forgetPassword(String email){
        User user=userRepo.findByEmail(email);
        if(user==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","User not found"));
        }

        String otp =generateOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
        userRepo.save(user);
        emailService.sendOtp(email,otp);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message","OTP sent to your email for reset your password. "));
    }
    @Transactional
    public ResponseEntity<Map<String,String>> resetPassword(String email,String password,String otp){
        User user=userRepo.findByEmail(email);
        if(user==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","User not found"));
        }
        if(!user.getOtp().equals(otp)){
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Invalid OTP"));
        }
        if(user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body(Map.of("message","OTP expired"));
        }
       String hashedPassword = passwordEncoder.encode(password);
       user.setPassword(hashedPassword);
        userRepo.save(user);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(Map.of("message","Password reset successfully."));
    }


    private String generateOtp(){
        int otp=100000+new Random().nextInt(900000);
        return String.valueOf(otp);
    }
    @Transactional
    public ResponseEntity<?> login(LoginDto loginDto) {
        String email= loginDto.getEmail();
        String password= loginDto.getPassword();

        User user= userRepo.findByEmail(email);
        if(user==null||!passwordEncoder.matches(password, user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        if(!user.isVerified()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Account not verified");
        }
        String token= jwtUtil.generateToken(email);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "type", "Bearer",
                "expiresIn", 3600));
    }
}
