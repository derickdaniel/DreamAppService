package com.datingapp.datingapp.controller;

import com.datingapp.datingapp.dto.ApiKeyBodyDto;
import com.datingapp.datingapp.dto.RegisterUserResponseDto;
import com.datingapp.datingapp.dto.UserRegistrationForm;
import com.datingapp.datingapp.entity.User;
import com.datingapp.datingapp.service.AuthenticationService;
import com.datingapp.datingapp.service.RegistrationService;
import com.datingapp.datingapp.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/register")
    /**
     * Registers a user in the database if they do not already exist and sends them an sms code in
     * order to verify them.
     */
    public RegisterUserResponseDto registerUser(@RequestBody final UserRegistrationForm registrationForm) {

        // TODO: Check inputs are valid:
        System.out.println(registrationForm);
        /* Create a new user */
        final User user;

        try {
            user = registrationService.registerUser(registrationForm);
            System.out.println(user);
        } catch (UserAlreadyExistsException e) {
            return null;
        }

        if (null == user) {
            return null;
        }

        /* Request a new access token for the user */
        final String requestId = "124"; //authService.requestAuthCode(registrationForm.getCountryCallingCode().getCode(),
                registrationForm.getRegionalPhoneNumber();

        return new RegisterUserResponseDto(requestId);
    }

}
