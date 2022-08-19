package com.example.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    @Async
    public void send(String to, String email) {
        SimpleMailMessage message = makeMessage(to, email);
        mailSender.send(message);
    }

    private SimpleMailMessage makeMessage(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Confirm your account");
        message.setFrom("cod.no-reply@yandex.ru");
        message.setText(text);
        return message;
    }
}
