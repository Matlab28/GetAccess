package com.example.getapi.service;

import com.example.getapi.dto.request.ConfirmRequestDto;
import com.example.getapi.dto.request.SignInRequestDto;
import com.example.getapi.entity.SignInEntity;
import com.example.getapi.exception.MyException;
import com.example.getapi.repository.SignInRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@Setter
@Getter
@RequiredArgsConstructor
public class SignInService {
    private final ModelMapper modelMapper;
    private final SignInRepository repository;
    private final JavaMailSender javaMailSender;
    private Random random = new Random();
    private int confirmation = random.nextInt(1000, 9999);

    public String signIn(SignInRequestDto dto) {
        SignInEntity map = modelMapper.map(dto, SignInEntity.class);
        String gmailCom = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b";
        String mailCom = "\\b[a-zA-Z0-9._%+-]+@mail\\.com\\b";
        String mailRu = "\\b[A-Za-z0-9._%+-]+@mail\\.ru\\b";

        if (!dto.getEmail().matches(gmailCom)
                && !dto.getEmail().matches(mailCom)
                && !dto.getEmail().matches(mailRu)) {
            return "Please enter the right email address";
        } else if (!dto.getPassword().matches(".*[A-Z].*")) {
            return "Password must contain at least an upper letter.";
        } else if (!dto.getPassword().matches(".*[a-z].*")) {
            return "Password must contain at least a lower letter.";
        } else if (!dto.getPassword().matches(".*\\d+.*")) {
            return "Password must contain  at least a number.";
        } else if (dto.getPassword().length() != 8) {
            return "Password's length must be 8 characters.";
        }

        if (!dto.getPassword().equals(dto.getPassConfirm())) {
            return "Invalid password, please try again";
        }

        repository.save(map);
        log.info("Info saved.");
        sendEmail(dto);
        return "Please confirm your email in the next step...->";
    }

    public String confirmation(ConfirmRequestDto dto) {
        if (Integer.parseInt(dto.getConfirmation()) == getConfirmation()) {
            return "Email confirmed successfully!\nThank you for your registration!" +
                    "\n\nYou can access telegram bot via this link:\n\nhttps://t.me/random_bookRecommender_bot";
        }
        return "Invalid confirmation code...";
    }


    public void sendEmail(SignInRequestDto dto) {
        if (dto.getEmail() == null) {
            log.error("Email address is null. Cannot send email.");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "Email Confirmation";
        String text = "Hi, thank you for your registration!" +
                "\n\nThis is your confirmation code : " + getConfirmation() + ".\n\nDo not share it";

        message.setFrom("matlab.abbaszada@gmail.com");
        message.setTo(dto.getEmail());
        message.setText(text);
        message.setSubject(subject);
        javaMailSender.send(message);
        log.info("Message sent to - " + dto.getEmail());
    }

    public String update(Long id, SignInRequestDto dto) throws MyException {
        SignInEntity signIn = repository.findById(id)
                .orElseThrow(() -> new MyException("Account not found by ID - " + id));

        modelMapper.map(signIn, dto);
        repository.save(signIn);
        return "Account updated successfully!";
    }

    public String delete(Long id) throws MyException {
        SignInEntity signIn = repository.findById(id)
                .orElseThrow(() -> new MyException("Account not found by ID - " + id));

        repository.delete(signIn);
        return "Account deleted successfully!";
    }
}
