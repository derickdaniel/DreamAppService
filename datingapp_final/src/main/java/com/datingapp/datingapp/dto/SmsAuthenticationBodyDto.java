package com.datingapp.datingapp.dto;

import com.datingapp.datingapp.PhoneCountryCodeEnum;

public class SmsAuthenticationBodyDto {

    /* The contents of a user request to authenticate themselves using sms. */

    private String requestId;
    private String authCode;
    private PhoneCountryCodeEnum countryCallingCode;

    /* Phone number includes the country calling code. */
    private String phoneNumber;

    public SmsAuthenticationBodyDto(final String requestId, final String authCode,
                                    final PhoneCountryCodeEnum countryCallingCode,
                                    final String regionalPhoneNumber) {
        this.requestId = requestId;
        this.authCode = authCode;
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = regionalPhoneNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(final String authCode) {
        this.authCode = authCode;
    }

    public PhoneCountryCodeEnum getCountryCallingCode() {
        return countryCallingCode;
    }

    public void setCountryCallingCode(final PhoneCountryCodeEnum countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
