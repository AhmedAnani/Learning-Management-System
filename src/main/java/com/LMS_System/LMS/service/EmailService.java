package com.LMS_System.LMS.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public  void sendOtp(String toEmail,String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("OTP Code");
        message.setText("Your OTP code is: " + otp + "\nValid for 5 minutes.");
        mailSender.send(message);
    }
}
