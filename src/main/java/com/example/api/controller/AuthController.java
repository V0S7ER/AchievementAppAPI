package com.example.api.controller;

import com.example.api.Global;
import com.example.api.model.User.User;
import com.example.api.model.User.UserCheckDTO;
import com.example.api.model.exception.BadRequestException;
import com.example.api.model.exception.NotFoundException;
import com.example.api.model.request.RegisterRequest;
import com.example.api.model.response.SimpleMessageResponse;
import com.example.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Optional;

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
    public ResponseEntity<SimpleMessageResponse> register(@RequestBody RegisterRequest registerRequest) throws BadRequestException {
        return ResponseEntity.of(Optional.of(authService.registration(registerRequest)));
    }

    @GetMapping("/confirm")
    @PermitAll()
    public ResponseEntity<SimpleMessageResponse> confirmRegistration(String token) throws NotFoundException {
        return ResponseEntity.of(Optional.of(authService.confirmRegistration(token)));
    }

    @GetMapping("/check")
    @PreAuthorize(value = Global.MAY_ALL_ROLES)
    public ResponseEntity<UserCheckDTO> check(@AuthenticationPrincipal User user) {
        return ResponseEntity.of(Optional.of(authService.check(user)));
    }
}
