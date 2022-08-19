package com.example.api.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UserRole {
    NEW("ROLE_NEW"),
    STUDENT("ROLE_STUDENT"),
    MODERATOR("ROLE_MODERATOR"),
    ADMIN("ROLE_ADMIN"),
    SUPERADMIN("ROLE_SUPERADMIN");

    private final String[] authority;

    UserRole(String... authority) {
        this.authority = authority;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return Stream.of(authority)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
