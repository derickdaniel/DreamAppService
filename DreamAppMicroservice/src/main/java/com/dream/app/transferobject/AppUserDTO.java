package com.dream.app.transferobject;

import java.util.Date;

import com.dream.app.entity.AppUser;

public class AppUserDTO {
	
	private Long userId;
	
    private String username;
 
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phone;
    
    private String role;
    
    private Date createdDate;
    
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
	
	public AppUser populateAppUser() {
		AppUser appUser = new AppUser();
		appUser.setUserId(this.getUserId());
		appUser.setFirstName(this.getFirstName());
		appUser.setLastName(this.getLastName());
		appUser.setUsername(this.getUsername());
		appUser.setPassword(this.getPassword());
		appUser.setPhone(this.getPhone());
		appUser.setEmail(this.getEmail());
		appUser.setRole(this.getRole());
		return appUser;
	}
       
}
