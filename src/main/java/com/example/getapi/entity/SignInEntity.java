package com.example.getapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "sign_in")
public class SignInEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email, password;

    @Column(name = "password_confirm")
    private String passConfirm;
}
