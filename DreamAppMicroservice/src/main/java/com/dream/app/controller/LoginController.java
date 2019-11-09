package com.dream.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.app.entity.AppUser;
import com.dream.app.exception.AppUserNotFoundException;
import com.dream.app.service.AppUserService;

@RestController
public class LoginController {
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AppUser> authenticate(@RequestBody @Valid AppUser appUser) {
		AppUser user = null;
		user = appUserService.getUserByEmail(appUser.getEmail());
		if(user != null) {
			return new ResponseEntity<AppUser>(user, HttpStatus.OK);
		}else {
			throw new AppUserNotFoundException("email-" + appUser.getEmail());
		}
	}

}
