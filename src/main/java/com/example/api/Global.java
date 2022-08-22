package com.example.api;

import java.time.format.DateTimeFormatter;

public class Global {
    /**
     * String constants
     */
    public static final String MAY_ALL_ROLES
            = "hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_SUPERADMIN')";
    public static final String CONFIRM_REGISTRATION_MSG = "Confirm your registration in letter we have sent to you";
    public static final String INCORRECT_EMAIL_MSG = "Email is incorrect";
    public static final String INCORRECT_PASSWORD_MSG = "Password is incorrect";
    public static final String INCORRECT_FIRST_NAME_MSG = "First name is incorrect";
    public static final String INCORRECT_LAST_NAME_MSG = "Last name is incorrect";
    public static final String USER_ALREADY_ENABLED_MSG = "User was already enabled";
    public static final String TOKEN_NOT_EXISTS_MSG = "Token doesn't exists";
    public static final String TOKEN_WAS_EXPIRED_MSG = "Token was expired";
    public static final String SUCCESS = "Success";
    public static final String CONFIRM_TOKEN_LINK = "http://localhost:8080/api/v1/confirm?token=";

    /**
     * Date constants
     */
    public static String DATETIME_PATTERN = "dd.MM.yyyy hh:mm";
    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /**
     * Number constants
     */
    public static final int PASSWORD_ENCODER_SECURE_LEVEL = 12;
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 20;

    public static final int NAME_MAX_LENGTH = 20;

    public static final int TOKEN_EXPIRE_TIME_MINUTES = 10;
}
