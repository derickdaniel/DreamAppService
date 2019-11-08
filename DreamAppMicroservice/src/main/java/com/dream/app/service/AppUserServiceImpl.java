package com.dream.app.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;
import com.dream.app.exception.AppUserNotFoundException;
import com.dream.app.repository.AppUserRepository;
import com.dream.app.repository.PersonalNoteRepository;
import com.dream.app.util.PasswordUtil;

@Service
public class AppUserServiceImpl implements AppUserService{
	
		private final AppUserRepository appUserRepository;
		private final PersonalNoteRepository personalNoteRepository;
		
		@Autowired
		public AppUserServiceImpl(final AppUserRepository appUserRepository, final PersonalNoteRepository personalNoteRepository) {
			this.appUserRepository = appUserRepository;
			this.personalNoteRepository = personalNoteRepository;
		}
		
		@Override
		public AppUser registerUser(AppUser user) throws Exception {
			user.setPassword(PasswordUtil.bycrypt(user.getPassword()));
			user.setRole("USER");
			user.setCreatedDate(new Timestamp(new Date().getTime()));
			user.setModifiedDate(new Timestamp(new Date().getTime()));
			user = this.save(user);
			return user;
		}
		
		@Override
		public AppUser updateUser(AppUser appUser) throws Exception {
			appUser.setModifiedDate(new Timestamp(new Date().getTime()));
			return this.save(appUser);
		}

		private AppUser save(AppUser appUser) throws Exception {
			try {
				appUser = appUserRepository.save(appUser);
			} catch (DataIntegrityViolationException e) {
			    if (e.getMostSpecificCause().getClass().getName().equals("org.postgresql.util.PSQLException") && ((SQLException) e.getMostSpecificCause()).getSQLState().equals("23505"))
			        throw new Exception("Username is already in use", e.getMostSpecificCause());
			    throw e;
			}	
			return appUser;
		}

		@Override
		public AppUser getUserByUsername(String userName) {
			 return appUserRepository.findByUsername(userName).orElseThrow(() -> new AppUserNotFoundException("user not found with username: "+userName));
		}
		
		@Override
		public AppUser getUserByEmail(String email) {
			 return appUserRepository.findByEmail(email).orElseThrow(() -> new AppUserNotFoundException("user not found with email: "+email));
		}

		@Override
		public PersonalNote savePersonalNote(PersonalNote note) {
			note = personalNoteRepository.save(note);
			return note;
		}

		@Override
		public List<PersonalNote> getPersonalNotesByAppUser(AppUser appUser) {
			List<PersonalNote> notes = personalNoteRepository.findByAppUser(appUser);
			return notes;
		}

}
