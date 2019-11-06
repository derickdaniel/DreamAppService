package com.dream.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.app.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	 Optional<AppUser> findByEmail(String email);
	 Optional<AppUser> findByUserName(String userName);

}
