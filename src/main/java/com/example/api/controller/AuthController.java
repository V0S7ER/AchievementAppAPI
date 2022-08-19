package com.example.api.controller;

import com.example.api.model.exception.BadRequestException;
import com.example.api.model.exception.NotFoundException;
import com.example.api.model.request.RegisterRequest;
import com.example.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("api/v1")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @PermitAll()
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws BadRequestException { // TODO: добавить респонсы на все реквесты
        authService.registration(registerRequest);
        return ResponseEntity.ok("Confirm registration in your mail!");
    }

    @GetMapping("/confirm")
    @PermitAll()
    public ResponseEntity<?> confirmRegistration(String token) throws NotFoundException {
        authService.confirmRegistration(token);
        return ResponseEntity.ok("You have confirmed your account!");
    }
}
