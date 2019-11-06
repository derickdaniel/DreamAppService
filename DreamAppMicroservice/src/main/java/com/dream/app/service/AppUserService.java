package com.dream.app.service;

import java.util.List;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;

public interface AppUserService {
	
	AppUser save(AppUser appUser) throws Exception;
	AppUser update(AppUser appUser) throws Exception;
	AppUser getByUserName(String userName);
	PersonalNote savePersonalNote(PersonalNote note);
	List<PersonalNote> getPersonalNotesByAppUser(AppUser appUser);
}
