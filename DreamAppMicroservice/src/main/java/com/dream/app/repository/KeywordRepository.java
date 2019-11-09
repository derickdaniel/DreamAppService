package com.dream.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.app.entity.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
	
	Optional<Keyword> findByName(String name);
}
