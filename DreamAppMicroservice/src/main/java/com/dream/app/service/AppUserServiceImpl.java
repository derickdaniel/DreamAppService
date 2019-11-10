package com.dream.app.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;
import com.dream.app.exception.AppUserNotFoundException;
import com.dream.app.repository.AppUserRepository;
import com.dream.app.repository.PersonalNoteRepository;
import com.dream.app.transferobject.PersonalNoteDTO;
import com.dream.app.util.PasswordUtil;

@Service
public class AppUserServiceImpl implements AppUserService {
	
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
			if(user.getRole() != null && !user.getRole().isEmpty()) {
				if(user.getRole().equalsIgnoreCase("ADMIN")) {
					user.setRole("ADMIN");
				}else {
					user.setRole("USER");
				}
			}else{
				user.setRole("USER");
			}
			user.setCreatedDate(new Timestamp(new Date().getTime()));
			user.setModifiedDate(new Timestamp(new Date().getTime()));
			user = this.save(user);
			return user;
		}
		
		@Override
		public AppUser updateUser(AppUser appUser) throws Exception {
			AppUser existingUser  = this.getUserByUserId(appUser.getUserId());
			if(existingUser == null) {
				throw new AppUserNotFoundException("User not found with id: "+appUser.getUserId());
			}
			appUser.setPassword(PasswordUtil.bycrypt(appUser.getPassword()));
			appUser.setRole(existingUser.getRole());
			appUser.setCreatedDate(existingUser.getCreatedDate());
			appUser.setModifiedDate(new Timestamp(new Date().getTime()));
			return this.save(appUser);
		}

		private AppUser save(AppUser appUser) throws Exception {
			try {
				appUser = appUserRepository.save(appUser);
			} catch (DataIntegrityViolationException e) {
			    if (e.getMostSpecificCause().getClass().getName().equals("org.postgresql.util.PSQLException") && ((SQLException) e.getMostSpecificCause()).getSQLState().equals("23505"))
			        throw new Exception("Username or email is already in use", e.getMostSpecificCause());
			    throw e;
			}	
			return appUser;
		}
		
		@Override
		public AppUser getUserByUserId(Long userId) {
			return appUserRepository.findByUserId(userId).orElseThrow(() -> new AppUserNotFoundException("user not found with user id: "+userId));
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
		public PersonalNote savePersonalNote(PersonalNoteDTO noteDTO) {
			PersonalNote note = noteDTO.populatePersonNote();
			AppUser appUser = this.getUserByUserId(noteDTO.getUserId());
			note.setAppUser(appUser);
			note = personalNoteRepository.save(note);
			return note;
		}

		@Override
		public List<PersonalNote> getPersonalNotesByAppUserId(Long userId) {
			AppUser appUser = this.getUserByUserId(userId);
			List<PersonalNote> notes = personalNoteRepository.findByAppUser(appUser);
			return notes;
		}

		@Override
		public void deletePersonalNote(Long personalNoteId) {
			personalNoteRepository.deleteById(personalNoteId);
		}

}
