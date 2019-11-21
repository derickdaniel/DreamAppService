package com.datingapp.datingapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    public UserProfile(Long userId, String name, Date dateOfBirth, String description) {
        this.userId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
