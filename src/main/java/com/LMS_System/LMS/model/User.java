package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "second_name",nullable = false)
    private String secondName;

    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String phone;

    @Column
    private String otp;

    @Column(name = "otp_expiration")
    private LocalDateTime otpExpiration;

    @Column(nullable = false)
    private boolean verified;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "users_courses",
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns=@JoinColumn(name = "course_id")
    )
    private Set<Course> courses;


}
