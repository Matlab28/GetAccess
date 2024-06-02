package com.example.getapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInResponseDto {
    private Long id;
    private String email, password, passConfirm;
}
