package com.datingapp.datingapp.service;

import com.datingapp.datingapp.dao.UserRepository;
import com.datingapp.datingapp.dto.UserRegistrationForm;
import com.datingapp.datingapp.entity.User;
import com.datingapp.datingapp.service.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository repo;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    /** Registers a user if the phone number does not already exist in the database.
     *
     * @param registrationForm The registration form provided by the user
     * @throws UserAlreadyExistsException If a user with the given phone number already exists
     * @return The user that was created
     */
    public User registerUser(final UserRegistrationForm registrationForm) throws UserAlreadyExistsException {

        final String countryCallingCode = registrationForm.getCountryCallingCode().getCode();
        final String phoneNumber = countryCallingCode + registrationForm.getRegionalPhoneNumber();

        if (repo.getNumUsersWithPhoneNumber(phoneNumber) > 0) {
            // user exists:
            LOGGER.info("User with the provided phone number already exists. New user not " +
                    "registered.");
            throw new UserAlreadyExistsException();
        }

        // user did not exist, create a new one:
        final User user = new User(countryCallingCode, phoneNumber);
        if (null != repo.save(user)) {
            LOGGER.info("New user registered and persisted to database!");
        }

        return user;
    }
}
