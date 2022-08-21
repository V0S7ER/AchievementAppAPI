package com.example.api.service.transformer;

import com.example.api.Global;
import com.example.api.model.User.User;
import com.example.api.model.User.UserCheckDTO;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer {
    public UserCheckDTO fromUserToUserCheckDTO(User user) {
        return new UserCheckDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().name(),
                user.getRegistrationDate().format(Global.DATE_TIME_FORMATTER)
        );
    }
}
