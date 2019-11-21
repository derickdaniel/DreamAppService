package com.datingapp.datingapp.dto;

import com.datingapp.datingapp.PhoneCountryCodeEnum;

public class SmsRequestBodyDto {

    /* The contents of a user request to get a sms verification code. */

    private PhoneCountryCodeEnum countryCallingCode;

    /* Phone number includes the country calling code. */
    private String phoneNumber;

    public SmsRequestBodyDto(final PhoneCountryCodeEnum countryCallingCode, final String regionalPhoneNumber) {
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = regionalPhoneNumber;
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
