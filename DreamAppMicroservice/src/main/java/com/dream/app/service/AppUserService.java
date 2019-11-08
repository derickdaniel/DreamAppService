package com.dream.app.service;

import java.util.List;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;

public interface AppUserService {
	
	AppUser registerUser(AppUser appUser) throws Exception;
	AppUser updateUser(AppUser appUser) throws Exception;
	AppUser getUserByUsername(String userName);
	AppUser getUserByEmail(String email);
	PersonalNote savePersonalNote(PersonalNote note);
	List<PersonalNote> getPersonalNotesByAppUser(AppUser appUser);
}
