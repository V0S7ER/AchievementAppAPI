package com.example.api.model.Confirmation;

import com.example.api.Global;
import com.example.api.model.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "confirmation_token", nullable = false)
    private String confirmationToken;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "expires_date", nullable = false)
    private LocalDateTime expiresDate;

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Columns

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmationToken confirmationToken = (ConfirmationToken) o;
        return id.equals(confirmationToken.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    } // Equals and hashCode

    /**
     * @param user User register constructor
     */
    public ConfirmationToken(User user) {
        this.user = user;
        confirmationToken = UUID.randomUUID().toString();
        createdDate = LocalDateTime.now();
        expiresDate = createdDate.plusMinutes(Global.TOKEN_EXPIRE_TIME_MINUTES);
    } // Constructors
}
