package com.example.api.controller;

import com.example.api.model.User.UserCheckDTO;
import com.example.api.model.exception.BadRequestException;
import com.example.api.model.exception.NotFoundException;
import com.example.api.model.request.LoginRequest;
import com.example.api.model.request.RegisterRequest;
import com.example.api.model.response.SimpleMessageResponse;
import com.example.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PermitAll
    public ResponseEntity<SimpleMessageResponse> register(@RequestBody RegisterRequest request) throws BadRequestException {
        return ResponseEntity.of(Optional.of(authService.registration(request)));
    }

    @GetMapping("/confirm")
    @PermitAll
    public ResponseEntity<SimpleMessageResponse> confirmRegistration(String token) throws NotFoundException {
        return ResponseEntity.of(Optional.of(authService.confirmRegistration(token)));
    }

    @GetMapping("/check")
    //@PreAuthorize(value = Global.MAY_ALL_ROLES)
    @PermitAll
    public ResponseEntity<UserCheckDTO> check() throws BadRequestException {
        return ResponseEntity.of(Optional.of(authService.check(SecurityContextHolder.getContext())));
    }

    @PostMapping("/auth")
    @PermitAll
    public ResponseEntity<?> auth(@RequestBody LoginRequest request) throws NotFoundException {
        authService.authorize(request, SecurityContextHolder.getContext());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
