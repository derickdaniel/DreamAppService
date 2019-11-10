package com.dream.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;
import com.dream.app.service.AppUserService;
import com.dream.app.transferobject.AppUserDTO;
import com.dream.app.transferobject.PersonalNoteDTO;

@RestController
@RequestMapping("/user")
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(value="/updateuser", method = RequestMethod.POST)
	public AppUser update(@RequestBody AppUserDTO appUserDTO) throws Exception {
		AppUser appUser = appUserDTO.populateAppUser();
		appUser = appUserService.updateUser(appUser);
		return appUser;
	}
	
	@RequestMapping(value="/addnote", method = RequestMethod.POST)
	public PersonalNote addNote(@RequestBody PersonalNoteDTO personalNoteDTO) throws Exception {
		PersonalNote note = appUserService.savePersonalNote(personalNoteDTO);
		return note;
	}
	
	@RequestMapping(value="/getnotes/{userId}", method = RequestMethod.GET)
	public List<PersonalNote> getNotes(@PathVariable Long userId) throws Exception {
		List<PersonalNote> notes = appUserService.getPersonalNotesByAppUserId(userId);
		return notes;
	}
	
	@RequestMapping(value="/deletenote/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<String> deleteNote(@PathVariable Long noteId) throws Exception {
		appUserService.deletePersonalNote(noteId);
		return ResponseEntity.ok().build();
	}
	
}
