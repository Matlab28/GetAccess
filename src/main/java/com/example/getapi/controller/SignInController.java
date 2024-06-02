package com.example.getapi.controller;

import com.example.getapi.dto.request.ConfirmRequestDto;
import com.example.getapi.dto.request.SignInRequestDto;
import com.example.getapi.exception.MyException;
import com.example.getapi.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class SignInController {
    private final SignInService service;

    @PostMapping("/sign-in")
    public String create(@RequestBody SignInRequestDto dto) {
        return service.signIn(dto);
    }

    @PostMapping("/confirm-email")
    public String confirm(@RequestBody ConfirmRequestDto dto) {
        return service.confirmation(dto);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody SignInRequestDto dto) throws MyException {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyException {
        return service.delete(id);
    }
}
