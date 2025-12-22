package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.LoginDto;
import com.LMS_System.LMS.DTO.RegisterDto;
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
    public ResponseEntity<Map<String, String>> register(RegisterDto registerDto) {

        // 1. Validate input
        if (registerDto.getEmail() == null || registerDto.getEmail().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email is required"));
        }

        if (registerDto.getPassword() == null || registerDto.getPassword().length() < 8) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Password must be at least 8 characters"));
        }

        // 2. Check if user already exists
        User user = userRepo.findByEmail(registerDto.getEmail());
         if (user != null) {

            // Optional: handle unverified users
            if (!user.isVerified()) {
               sentOtp(registerDto.getEmail());

                return ResponseEntity.status(HttpStatus.OK)
                        .body(Map.of("message", "OTP resent to your email"));
            }

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "You already have an account"));
        }

        // 3. Create new user
        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setFirstName(registerDto.getFirst_name());
        newUser.setSecondName(registerDto.getSecond_name());
        newUser.setPhone(registerDto.getPhone());
        newUser.setBirthDate(registerDto.getBirth_date());

        // 4. Encrypt password
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // 5. Save user
        userRepo.save(newUser);

        // 6.sent otp
        sentOtp(registerDto.getEmail());


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "OTP sent to your email for verification"));
    }

    @Transactional
    public ResponseEntity<Map<String,String>> verifyOtp(String email,String otp){
        User user=userRepo.findByEmail(email);
        if(user.getOtpExpiration().isBefore(LocalDateTime.now()) &&
               ! user.isVerified()){
            sentOtp(user.getEmail());
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).
                    body(Map.of("message","Wil sent otp again"));

        }
        if(user.isVerified()) {
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body(Map.of("message", "Already verified"));
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

        sentOtp(email);
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



    @Transactional
    public ResponseEntity<Map<String, ?>> login(LoginDto loginDto) {

        // 1. Basic validation
        if (loginDto.getEmail() == null || loginDto.getEmail().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email is required"));
        }

        if (loginDto.getPassword() == null || loginDto.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Password is required"));
        }

        // 2. Find user
        User user = userRepo.findByEmail(loginDto.getEmail());

        // Security best practice: do not reveal which field is wrong
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        // 3. Check verification
        if (!user.isVerified()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Account not verified. Please verify your email."));
        }

        // 4. Check password
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        // 5. Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // 6. Build response
        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "type", "Bearer",
                        "expiresIn", "1 Hour"
                )
        );
    }


    @Transactional
    public ResponseEntity<Map<String,?>> userProfile(String email){
        User user=userRepo.findByEmail(email);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","User not found. Please create an account."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("fullName", user.getFirstName()+" "+user.getSecondName()
                        ,"email", user.getEmail()
                        ,"phone", user.getPhone()
                        ,"birthDate",user.getBirthDate()));
    }

    private void sentOtp(String email){
        User user = userRepo.findByEmail(email);
        String otp=generateOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
        userRepo.save(user);
        emailService.sendOtp(email,otp);

    }
    private String generateOtp(){
        int otp=100000+new Random().nextInt(900000);
        return String.valueOf(otp);
    }
}
