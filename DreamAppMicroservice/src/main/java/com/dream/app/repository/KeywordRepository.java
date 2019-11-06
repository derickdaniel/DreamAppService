package com.dream.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
	
	List<Keyword> findByName(String name);
}
