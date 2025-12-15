package com.LMS_System.LMS.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@NoArgsConstructor
public class JwtUtil {

    private static final String SECRET_KEY
            ="m}atu^p;W*%nMZs5#I+4sRp@al$yHjVU?_}6-smmH1q";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(String email){
         return Jwts
                 .builder()
                 .setSubject(email)
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                 .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),SignatureAlgorithm.HS256)
                 .compact();
     }

     public String extractEmail(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
     }

     public boolean isTokenValid(String token){
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
     }
}
