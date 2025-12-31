package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.user.GetUserResponseDto;
import com.LMS_System.LMS.dto.user.UserProfileDto;
import com.LMS_System.LMS.exception.ConflictHandling;
import com.LMS_System.LMS.exception.GateWayTimeOut;
import com.LMS_System.LMS.exception.NotAcceptable;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.auth.LoginDto;
import com.LMS_System.LMS.dto.auth.RegisterDto;
import com.LMS_System.LMS.component.JwtUtil;
import com.LMS_System.LMS.dto.auth.ResetPasswordDto;
import com.LMS_System.LMS.dto.auth.VerifyOtpDto;
import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.repository.RoleRepository;
import com.LMS_System.LMS.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1. Add user
    @Transactional
    public ResponseDto register (RegisterDto registerDto) {


        User user = userRepo.findByEmail(registerDto.getEmail());
         if (user != null) {


            if (!user.isVerified()&&user.getOtpExpiration().isBefore(LocalDateTime.now())) {
                    sentOtp(registerDto.getEmail());
                    throw  new GateWayTimeOut("Verification OTP resent to your email");
            }

            throw  new ConflictHandling("You already have an account");
        }


        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setFirstName(registerDto.getFirst_name());
        newUser.setSecondName(registerDto.getSecond_name());
        newUser.setPhone(registerDto.getPhone());
        newUser.setBirthDate(registerDto.getBirth_date());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userRepo.save(newUser);
        sentOtp(registerDto.getEmail());

        return new ResponseDto( "OTP sent to your email for verification");
    }

    // 2. Verify otp
    @Transactional
    public ResponseDto verifyOtp(VerifyOtpDto verifyOtpDto){
        User user=userRepo.findByEmail(verifyOtpDto.getEmail());
        if(user.getOtpExpiration().isBefore(LocalDateTime.now()) &&
               ! user.isVerified()){
            sentOtp(user.getEmail());
            throw new GateWayTimeOut("Wil sent otp again");

        }
        if(user.isVerified()) {
            throw new GateWayTimeOut("Already verified");
        }
        if(!user.getOtp().equals(verifyOtpDto.getOtp())){
            throw new NotAcceptable("Invalid OTP");
        }
        user.setVerified(true);
        user.setOtp(null);
        userRepo.save(user);
        return new ResponseDto("Account verified successfully.");
    }

    // 3. Forget password

    public ResponseDto forgetPassword(String email){
        User user=userRepo.findByEmail(email);
        if(user==null){
            throw new NotFound("User not found");
        }

        sentOtp(email);
        return new ResponseDto("OTP sent to your email for reset your password. ");
    }

    // 4. Reset password

    public ResponseDto resetPassword (ResetPasswordDto resetPasswordDto){
        User user=userRepo.findByEmail((resetPasswordDto.getEmail()));
        if(user==null){
            throw new NotFound("User not found");
        }
        if(!user.getOtp().equals(resetPasswordDto.getOtp())){
            throw new NotAcceptable("Invalid OTP");
        }
        if(user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            throw new GateWayTimeOut("OTP expired");
        }
        String hashedPassword = passwordEncoder.encode(resetPasswordDto.getPassword());
        user.setPassword(hashedPassword);
        userRepo.save(user);
        return new ResponseDto("Password reset successfully.");
    }


    // 5. Login

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

    // 6. Get user profile

    public GetUserResponseDto userProfile(String email){
        User user=userRepo.findByEmail(email);
        if (user==null){
            throw new NotFound("User not found. Please create an account.");
        }
        return new GetUserResponseDto( user.getFirstName()
                        ,user.getSecondName()
                        , user.getEmail()
                        , user.getPhone()
                        ,user.getBirthDate());
    }

    // 7. Get all users
    public List<GetUserResponseDto> getAllUsers(){
        return userRepo.findAll()
                .stream()
                .map(user -> new GetUserResponseDto(user.getFirstName()
                        ,user.getSecondName()
                        , user.getEmail()
                        , user.getPhone()
                        ,user.getBirthDate()
                )).toList();
    }
    // Delete user by userEmail
    public ResponseDto deleteUserByEmail(UserProfileDto userProfileDto){
        User user=userRepo.findByEmail(userProfileDto.getEmail());
        if(user==null) {
            throw new NotFound("User not found");
        }
        userRepo.deleteById(user.getId());
        return new ResponseDto("User deleted successfully");
    }

    // 8. Send otp
    private void sentOtp(String email){
        User user = userRepo.findByEmail(email);
        String otp=generateOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
        userRepo.save(user);
        emailService.sendOtp(email,otp);

    }
    // 9. Generate otp
    private String generateOtp(){
        int otp=100000+new Random().nextInt(900000);
        return String.valueOf(otp);
    }
}
