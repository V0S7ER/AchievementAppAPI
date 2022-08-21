package com.example.api.service;

import com.example.api.Global;
import com.example.api.model.Confirmation.ConfirmationToken;
import com.example.api.model.Confirmation.ConfirmationTokenRepository;
import com.example.api.model.User.*;
import com.example.api.model.exception.BadRequestException;
import com.example.api.model.exception.NotFoundException;
import com.example.api.model.request.RegisterRequest;
import com.example.api.model.response.SimpleMessageResponse;
import com.example.api.service.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final ValidatorService validator;
    private final EmailService emailService;
    private final UserTransformer userTransformer;

    @Transactional
    public SimpleMessageResponse registration(RegisterRequest request) throws BadRequestException {
        SimpleMessageResponse response
                = new SimpleMessageResponse(true, Global.CONFIRM_REGISTRATION_MSG);
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        validator.validateRegister(request); // Validate data

        if (user != null) {
            if (user.getEnabled()) {//if user was already enabled
                throw new BadRequestException(Global.USER_ALREADY_ENABLED_MSG);
            }

            List<ConfirmationToken> tokenList = confirmationTokenRepository.findByUser(user);
            List<ConfirmationToken> deleteList = tokenList
                    .stream()
                    .filter(token -> token.getExpiresDate().isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());
            tokenList = tokenList
                    .stream()
                    .filter((token) -> token.getExpiresDate().isAfter(LocalDateTime.now()))
                    .collect(Collectors.toList()); //There is a possibility to use removeAll(deleteList), but idk

            confirmationTokenRepository.deleteAll(deleteList);

            if (!tokenList.isEmpty()) {
                throw new BadRequestException(Global.CONFIRM_REGISTRATION_MSG);
            }
        }

        signUp(user, request);
        return response;
    }

    public SimpleMessageResponse confirmRegistration(String confirmationToken) throws NotFoundException {
        SimpleMessageResponse response
                = new SimpleMessageResponse(true, Global.SUCCESS);
        ConfirmationToken token = confirmationTokenRepository
                .findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new NotFoundException(Global.TOKEN_NOT_EXISTS_MSG));

        if (token.getExpiresDate().isBefore(LocalDateTime.now())) {
            confirmationTokenRepository.delete(token);
            throw new NotFoundException(Global.TOKEN_WAS_EXPIRED_MSG);
        }

        User user = token.getUser();
        user.setEnabled(true);
        user.setRole(UserRole.STUDENT); //TODO: при добавлении модерации убрать эту строку
        userRepository.save(user);
        confirmationTokenRepository.delete(token);

        return response;
    }

    public UserCheckDTO check(User user) {
        return userTransformer.fromUserToUserCheckDTO(user);
    }

    private void signUp(User user, RegisterRequest request) {
        user = new User(user, request);
        ConfirmationToken token = new ConfirmationToken(user);
        userRepository.save(user);
        confirmationTokenRepository.save(token);

        emailService.send(user.getEmail(), buildEmail(user.getFirstName(), token.getConfirmationToken()));
    }

    private String buildEmail(String firstName, String token) {
        String link = Global.CONFIRM_TOKEN_LINK + token;
        return "Hello, " + firstName + "!\n" +
                "Please confirm your registration with a link: " + link;
    }
}
