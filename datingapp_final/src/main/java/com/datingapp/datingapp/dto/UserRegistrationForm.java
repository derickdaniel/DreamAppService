package com.datingapp.datingapp.dto;

import com.datingapp.datingapp.PhoneCountryCodeEnum;

import java.util.Date;

public class UserRegistrationForm {

    /* Represents a user registration form from the front end. */

    private String name;
    private Date dateOfBirth;
    private PhoneCountryCodeEnum countryCallingCode;
    private String regionalPhoneNumber;
    private String email;

    public UserRegistrationForm(final String name, final Date dateOfBirth,
                                final PhoneCountryCodeEnum countryCallingCode,
                                final String regionalPhoneNumber, final String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.countryCallingCode = countryCallingCode;
        this.regionalPhoneNumber = regionalPhoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PhoneCountryCodeEnum getCountryCallingCode() {
        return countryCallingCode;
    }

    public void setCountryCallingCode(final PhoneCountryCodeEnum countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    public String getRegionalPhoneNumber() {
        return regionalPhoneNumber;
    }

    public void setRegionalPhoneNumber(final String regionalPhoneNumber) {
        this.regionalPhoneNumber = regionalPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "name: " + name + ", dateOfBirth: " + dateOfBirth + ", phone: +"
                + countryCallingCode.getCode() + regionalPhoneNumber;
    }
}
