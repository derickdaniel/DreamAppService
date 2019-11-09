package com.dream.app.service;

import java.util.List;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;
import com.dream.app.transferobject.PersonalNoteDTO;

public interface AppUserService {
	
	AppUser registerUser(AppUser appUser) throws Exception;
	AppUser updateUser(AppUser appUser) throws Exception;
	AppUser getUserByUserId(Long userId);
	AppUser getUserByUsername(String userName);
	AppUser getUserByEmail(String email);
	PersonalNote savePersonalNote(PersonalNoteDTO noteDTO);
	List<PersonalNote> getPersonalNotesByAppUserId(Long userId);
}
