package com.dream.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "App_User", uniqueConstraints = { @UniqueConstraint(name = "APP_USER_UK", columnNames = "username"),
		@UniqueConstraint(name = "APP_USER_UK2", columnNames = "email")})
public class AppUser {
	
    @Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)
    private Long userId;
 
    @Column(name = "username", length = 36, nullable = false)
    private String username;
 
    @Column(name = "password", length = 128, nullable = false)
    @JsonIgnore
    private String password;
    
    @Column(name = "First_Name", length = 60, nullable = false)
    private String firstName;
    
    @Column(name = "Last_Name", length = 60, nullable = false)
    private String lastName;
    
    @Column(name = "Email", length = 128, nullable = false)
    private String email;
    
    @Column(name = "Phone", length = 10, nullable = false)
    private String phone;
    
    @Column(name = "Role", length = 5, nullable = false)
    private String role;
    
    @Column(name = "Created_Date", length = 36, nullable = false)
    private Date createdDate;
    
    @Column(name = "Modified_Date", length = 36, nullable = false)
    private Date modifiedDate;
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
    
}
