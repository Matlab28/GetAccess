package com.example.getapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInRequestDto {
    private String email, password, passConfirm;
}
