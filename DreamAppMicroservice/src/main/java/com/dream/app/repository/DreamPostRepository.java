package com.dream.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.DreamPost;

public interface DreamPostRepository extends JpaRepository<DreamPost, Long>{
	
	List<DreamPost> findByAppUser(AppUser appUser);
}
