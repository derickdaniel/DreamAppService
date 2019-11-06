package com.dream.app.service;

import java.util.List;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;

public interface DreamPostService {
	
	List<DreamPost> findPostByAppUser(AppUser appUser);
	DreamPost createPost(DreamPost post);
	DreamPost updatePost(DreamPost post);
	Comment saveComment(Comment comment);
	Keyword saveKeyword(Keyword keyword);
	List<Comment> getCommentsByDreamPost(DreamPost dreamPost);
	List<Comment> getCommentsByAppUser(AppUser appUser);

}
