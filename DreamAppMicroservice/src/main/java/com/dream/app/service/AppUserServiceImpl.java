package com.dream.app.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;
import com.dream.app.repository.AppUserRepository;
import com.dream.app.repository.PersonalNoteRepository;

@Service
public class AppUserServiceImpl implements AppUserService{
	
		private final AppUserRepository appUserRepository;
		private final PersonalNoteRepository personalNoteRepository;
		
		@Autowired
		public AppUserServiceImpl(final AppUserRepository appUserRepository, final PersonalNoteRepository personalNoteRepository) {
			this.appUserRepository = appUserRepository;
			this.personalNoteRepository = personalNoteRepository;
		}
		
		public AppUser registerUser(AppUser user) {
			user = appUserRepository.save(user);
			return user;
		}

		@Override
		public AppUser save(AppUser appUser) throws Exception {
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
		public AppUser getByUserName(String userName) {
			 return appUserRepository.findByUserName(userName).orElseThrow(() -> new EntityNotFoundException(userName));
		}

		@Override
		public AppUser update(AppUser appUser) throws Exception {
			return this.save(appUser);
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
