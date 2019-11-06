package com.dream.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByDreamPost(DreamPost dreamPost);
	List<Comment> findByAppUser(AppUser appUser);
}
