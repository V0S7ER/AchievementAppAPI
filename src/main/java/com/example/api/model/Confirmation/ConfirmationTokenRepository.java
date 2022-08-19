package com.example.api.model.Confirmation;

import com.example.api.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);

    List<ConfirmationToken> findByUser(User user);
}
