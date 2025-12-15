package com.LMS_System.LMS.component;

import com.LMS_System.LMS.model.User;
import com.LMS_System.LMS.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@NoArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null&& authHeader.startsWith("Bearer ")){

            String token=authHeader.substring(7);

            if(jwtUtil.isTokenValid(token)){
                String email=jwtUtil.extractEmail(token);
                User user=userRepository.findByEmail(email);
                if(user!=null){
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(
                                    email,null, List.of()
                            );
                    SecurityContextHolder.getContext()
                            .setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
