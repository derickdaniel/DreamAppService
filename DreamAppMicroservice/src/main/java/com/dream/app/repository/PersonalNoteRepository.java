package com.dream.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.PersonalNote;

public interface PersonalNoteRepository extends JpaRepository<PersonalNote, Long>{
	
	List<PersonalNote> findByAppUser(AppUser appUser);
}
