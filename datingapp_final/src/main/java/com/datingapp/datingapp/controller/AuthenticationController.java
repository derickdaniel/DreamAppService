package com.datingapp.datingapp.controller;

import com.datingapp.datingapp.controller.exception.InvalidInputException;
import com.datingapp.datingapp.dto.SmsAuthenticationBodyDto;
import com.datingapp.datingapp.dto.SmsRequestBodyDto;
import com.datingapp.datingapp.service.AuthenticationService;
import com.datingapp.datingapp.service.InformationService;
import com.datingapp.datingapp.service.exception.UserDoesNotExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private InformationService infoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final int AUTH_CODE_LENGTH = 6;
    private static final int MIN_REGIONAL_PHONE_NUMBER_LENGTH = 8;
    private static final int MAX_REGIONAL_PHONE_NUMBER_LENGTH = 12;

    @RequestMapping("/login/request-code")
    /**
     * Sends an sms code to the user's phone. Returns the requestId (used with the sms code to
     * verify the user).
     */
    public String triggerSmsVerificationCode(@RequestBody final SmsRequestBodyDto body)
            throws InvalidInputException {
        System.out.println("Requested code!");
        System.out.println(body.getPhoneNumber());
        System.out.println(body.getCountryCallingCode());
        // do not execute request if input is known to be invalid:
        if (!isValidSmsRequestBody(body)) {
            throw new InvalidInputException();
        }

        return authService.requestAuthCode(body.getPhoneNumber());
    }

    @RequestMapping("/login/submit-code")
    /**
     * Submits the sms code and requestId to authenticate the user.
     */
    public String authenticate(@RequestBody final SmsAuthenticationBodyDto body)
            throws UserDoesNotExistException, InvalidInputException {
        System.out.println(body.getAuthCode());
        System.out.println(body.getRequestId());
        System.out.println(body.getCountryCallingCode());
        System.out.println(body.getPhoneNumber());
        // do not execute request if input is known to be invalid:
        if (!isValidSmsAuthenticationBody(body)) {
            throw new InvalidInputException();
        }

        return authService.submitAuthCode(body.getRequestId(), body.getAuthCode(), body.getPhoneNumber());
    }

    @RequestMapping("/login/info")
    /**
     *  Returns relevant information required for logging in, such as phone country codes.
     */
    public List<String> getInfo() {

        return infoService.getLoginInfo();
    }

    /** Checks if the SmsRequestBodyDto is valid
     *
     * @param body The SmsRequestBodyDto to check
     * @return Returns false if input is known to be invalid, true otherwise
     */
    private boolean isValidSmsRequestBody(final SmsRequestBodyDto body) {

        final String countryCallingCode = body.getCountryCallingCode().getCode();
        final String regionalPhoneNumber = body.getPhoneNumber();

        return isValidPhoneNumber(countryCallingCode, regionalPhoneNumber);
    }

    /** Checks if the SmsAuthenticationBodyDto is valid
     *
     * @param body The SmsAuthenticationBodyDto to check
     * @return Returns false if input is known to be invalid, true otherwise
     */
    private boolean isValidSmsAuthenticationBody(final SmsAuthenticationBodyDto body) {

        final String requestId = body.getRequestId();
        if (null == requestId) {
            return false;
        }

        final String authCode = body.getAuthCode();
        if (null == authCode) {
            return false;
        }
        if (authCode.length() != AUTH_CODE_LENGTH) {
            return false;
        }

        final String countryCallingCode = body.getCountryCallingCode().getCode();
        final String regionalPhoneNumber = body.getPhoneNumber();

        return isValidPhoneNumber(countryCallingCode, regionalPhoneNumber);

    }

    /** Checks if a phone number has a potentially valid format
     *
     * @param countryCallingCode The country calling code
     * @param regionalPhoneNumber The regional phone number
     * @return Returns false if input is known to be invalid, true otherwise
     */
    private boolean isValidPhoneNumber(final String countryCallingCode,
                                       final String regionalPhoneNumber) {
        if (null == countryCallingCode) {
            return false;
        }
        if (countryCallingCode.length() < 1 || countryCallingCode.length() > 3) {
            return false;
        }

        if (regionalPhoneNumber == null) {
            return false;
        }
        if (regionalPhoneNumber.length() < MIN_REGIONAL_PHONE_NUMBER_LENGTH
                || regionalPhoneNumber.length() > MAX_REGIONAL_PHONE_NUMBER_LENGTH) {
            return false;
        }
        return true;
    }
}


