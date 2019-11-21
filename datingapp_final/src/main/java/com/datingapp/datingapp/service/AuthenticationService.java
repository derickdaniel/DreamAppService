package com.datingapp.datingapp.service;

import com.datingapp.datingapp.MockApiKeyGenerator;
import com.datingapp.datingapp.controller.AuthenticationController;
import com.datingapp.datingapp.dao.UserRepository;
import com.datingapp.datingapp.entity.User;
import com.datingapp.datingapp.service.exception.UserDoesNotExistException;
import com.messagebird.MessageBirdClient;
import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.NotFoundException;
import com.messagebird.exceptions.UnauthorizedException;
import com.messagebird.objects.Verify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authenticationService")
public class AuthenticationService {

    @Autowired
    private UserRepository repo;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String MESSAGE_BIRD_API_KEY = "03d3ON8CAa47nJ6ULQOgpeFdo";
    private static final MessageBirdService birdService = new MessageBirdServiceImpl(MESSAGE_BIRD_API_KEY);
    private static final MessageBirdClient birdClient = new MessageBirdClient(birdService);

    /** Sends a sms code to the phone number
     *
     * @param phoneNumber The phone number of a user, including the country calling code
     * @return The third-party requestId, needed for authentication with the sms code
     */
    public String requestAuthCode(final String phoneNumber) {

        final MessageBirdService birdService = new MessageBirdServiceImpl(MESSAGE_BIRD_API_KEY);
        final MessageBirdClient birdClient = new MessageBirdClient(birdService);

        final Verify verify;
        try {
            verify = birdClient.sendVerifyToken(phoneNumber);
            System.out.println("id: " + verify.getId());
        } catch (UnauthorizedException | GeneralException e) {
            LOGGER.error("Failed to send a authorization code to user with phone number: "
                    + phoneNumber);
            if (null != e.getErrors()) {
                LOGGER.error(e.getErrors().toString());
            }
            e.printStackTrace();
            return null;
        }
        return verify.getId();
    }

    /** Checks to see  if the requestId and authorization code are a correct match.
     *
     * @param requestId The id from the third-party
     * @param authCode The sms code that was sent to the user
     * @param phoneNumber The phone number of a user, including the country calling code
     * @return An API access key for the user
     */
    public String submitAuthCode(final String requestId, final String authCode,
                                 final String phoneNumber)
            throws UserDoesNotExistException {

        // If user doesn't exist we don't want to waste a third-party API call:
        if (repo.getNumUsersWithPhoneNumber(phoneNumber) <= 0) {
            throw new UserDoesNotExistException();
        }

        final Verify verify;
        try {
            verify = birdClient.verifyToken(requestId, authCode);
            System.out.println("id: " + verify.getId());

        } catch (UnauthorizedException | GeneralException | NotFoundException e) {
            LOGGER.error("Failed to verify token to user with request id: "
                    + requestId + ", and authorization code: " + authCode);
            if (null != e.getErrors()) {
                LOGGER.error(e.getErrors().toString());
            }
            e.printStackTrace();
            return null;
        }

        // User has been validated here

        return authorizeUser(phoneNumber);
    }

    /** Generates an API access key for the user only if the user exists. API key is stored in the
     *  database.
     *
     * @param phoneNumber The phone number of a user, including the country calling code.
     * @return An API access key for the backend services
     */
    private String authorizeUser(final String phoneNumber)
            throws UserDoesNotExistException {

        final String backendAPIKey = MockApiKeyGenerator.generate128CharKey(phoneNumber);

        final User user = findUserWithPhoneNumber(phoneNumber);

        // update user with new API key:
        user.setApiKey(backendAPIKey);
        repo.save(user);

        return backendAPIKey;
    }

    /** Finds a user with the given phoneNumber. If none is found, then a
     *  Runtime exception is thrown.
     *
     * @param phoneNumber The phone number of a user, including the country calling code
     * @throws UserDoesNotExistException
     * @return A user entity - either one that already existed, or a newly created user
     */
    private User findUserWithPhoneNumber(final String phoneNumber)
            throws UserDoesNotExistException {

        final List<User> usersWithPhoneNumber = repo.getUserByPhoneNumber(phoneNumber);

        // No user exists with this phone number:
        if (null == usersWithPhoneNumber || usersWithPhoneNumber.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        return usersWithPhoneNumber.get(0);
    }

}
