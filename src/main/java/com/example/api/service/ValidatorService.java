package com.example.api.service;

import com.example.api.Global;
import com.example.api.model.exception.BadRequestException;
import com.example.api.model.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    private static final String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,6}$";

    public void validateRegister(RegisterRequest request) throws BadRequestException {
        boolean correctEmail = validateEmail(request.getEmail());
        if (!correctEmail)
            throw new BadRequestException(Global.INCORRECT_EMAIL_MSG);

        boolean correctPassword = validatePassword(request.getPassword());
        if (!correctPassword)
            throw new BadRequestException(Global.INCORRECT_PASSWORD_MSG);

        boolean correctFirstName = validateNameOrSurname(request.getFirstName());
        if (!correctFirstName)
            throw new BadRequestException(Global.INCORRECT_FIRST_NAME_MSG);

        boolean correctLastName = validateNameOrSurname(request.getLastName());
        if (!correctLastName)
            throw new BadRequestException(Global.INCORRECT_LAST_NAME_MSG);
    }

    private boolean validateEmail(String email) {
        return email.matches(emailRegex);
    }

    private boolean validatePassword(String password) {
        return Global.PASSWORD_MIN_LENGTH <= password.length() && password.length() <= Global.PASSWORD_MAX_LENGTH;
    }

    private boolean validateNameOrSurname(String name) {
        return name.length() <= Global.NAME_MAX_LENGTH;
    }
}
