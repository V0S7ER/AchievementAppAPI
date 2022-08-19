package com.example.api.model.User;

import com.example.api.model.Achievement.Achievement;
import com.example.api.model.request.RegisterRequest;
import com.example.api.security.PasswordEncoderFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean locked = false;

    @Column(nullable = false)
    private Boolean enabled = false;

    @Column(nullable = false, name = "registration_date")
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum ('NEW', 'STUDENT', 'MODERATOR', 'ADMIN', 'SUPERADMIN')")
    private UserRole role = UserRole.NEW; // Columns

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Achievement> achievementList; // ManyToOne

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    } // UserDetailsImpl

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    } // Equals and hashCode

    public User(User user, RegisterRequest request) {
        this(request);
        if(user != null)
            this.id = user.getId();
    }

    public User(RegisterRequest request) {
        firstName = request.getFirstName();
        lastName = request.getLastName();
        email = request.getEmail();
        password = PasswordEncoderFactory.getPasswordEncoder().encode(request.getPassword());
        registrationDate = LocalDateTime.now();
    }  // Constructors
}
