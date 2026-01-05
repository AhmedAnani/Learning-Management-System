package com.LMS_System.LMS.config;

import com.LMS_System.LMS.component.JwtFilter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@NoArgsConstructor
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c->c.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/role/**").hasRole("admin")
                        .requestMatchers("/permission/**").hasRole("admin")
                        .requestMatchers("/user/**").hasRole("admin")
                        .requestMatchers(HttpMethod.GET,"/course").permitAll()
                        .requestMatchers(HttpMethod.GET,"/course/my").hasRole("creator")
                        .requestMatchers(HttpMethod.POST,"/course").hasRole("creator")
                        .requestMatchers(HttpMethod.DELETE,"/course").hasRole("admin")
                        .requestMatchers(HttpMethod.POST,"/section").hasRole("creator")
                        .requestMatchers(HttpMethod.DELETE,"/section").hasRole("admin")
                        .requestMatchers(HttpMethod.GET,"/section/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/content").hasRole("creator")
                        .requestMatchers(HttpMethod.DELETE,"/content").hasRole("admin")
                        .requestMatchers(HttpMethod.GET,"/content/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/article").hasRole("creator")
                        .requestMatchers(HttpMethod.DELETE,"/article").hasRole("admin")
                        .requestMatchers(HttpMethod.GET,"/article/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/video").hasRole("creator")
                        .requestMatchers(HttpMethod.DELETE,"/video").hasRole("admin")
                        .requestMatchers(HttpMethod.GET,"/video/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/quiz").hasRole("creator")
                        .requestMatchers(HttpMethod.DELETE,"/quiz").hasRole("admin")
                        .requestMatchers(HttpMethod.GET,"/quiz/**").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
