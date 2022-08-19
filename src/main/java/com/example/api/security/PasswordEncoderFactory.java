package com.example.api.security;

import com.example.api.Global;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderFactory {
    private static final BCryptPasswordEncoder passwordEncoder
            = new BCryptPasswordEncoder(Global.PASSWORD_ENCODER_SECURE_LEVEL);

    public static PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
