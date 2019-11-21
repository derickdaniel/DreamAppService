package com.datingapp.datingapp.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",  initialValue=1, allocationSize = 1, sequenceName = "users_user_id_seq")
    @Column(name = "user_id")
    private Long userId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name="api_key", nullable = true, length=128)
    private String apiKey = null;

    @Column(name = "country_calling_code", nullable = false, length=3)
    private String countryCallingCode;

    /* The full phone number, including the country calling code */
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public User() {}

    public User(final User user) {
        this.phoneNumber = user.phoneNumber;
        this.countryCallingCode = user.countryCallingCode;
        this.apiKey = user.apiKey;
        this.creation_date = user.creation_date;
    }

    public User(final String countryCallingCode, final String phoneNumber) {
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
    }

    public User(final String apiKey, final String countryCallingCode, final String phoneNumber) {
        this.apiKey = apiKey;
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCountryCallingCode() {
        return countryCallingCode;
    }

    public void setCountryCallingCode(final String countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "userId: " + userId + ", creationDate: " + creation_date + ", apiKey: " + apiKey
                + " countryCallingCode: " + countryCallingCode + ", phoneNumber: " + phoneNumber;
    }
}
