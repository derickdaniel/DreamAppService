package com.dream.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;
import com.dream.app.repository.CommentRepository;
import com.dream.app.repository.DreamPostRepository;
import com.dream.app.repository.KeywordRepository;

@Service
@Transactional
public class DreamPostServiceImpl implements DreamPostService {

	private final DreamPostRepository dreamPostRepository;
	private final CommentRepository commentRepository; 
	private final KeywordRepository keywordRepository;
	
	@Autowired
	public DreamPostServiceImpl(final DreamPostRepository dreamPostRepository, final CommentRepository commentRepository, final KeywordRepository keywordRepository) {
		this.dreamPostRepository = dreamPostRepository;
		this.commentRepository = commentRepository;
		this.keywordRepository = keywordRepository;
	}
	
	@Override
	public List<DreamPost> findPostByAppUser(AppUser appUser) {
		List<DreamPost> dreams = dreamPostRepository.findByAppUser(appUser);
		return dreams;
	}

	@Override
	public DreamPost createPost(DreamPost post) {
		post =  dreamPostRepository.save(post);
		return post;
	}
	
	@Override
	public DreamPost updatePost(DreamPost post) {
		post =  dreamPostRepository.save(post);
		return post;
	}
	
	@Override
	public Comment saveComment(Comment comment) {
		comment = commentRepository.save(comment);
		return comment;
	}

	@Override
	public List<Comment> getCommentsByDreamPost(DreamPost dreamPost) {
		List<Comment> comments = commentRepository.findByDreamPost(dreamPost);
		return comments;
	}

	@Override
	public List<Comment> getCommentsByAppUser(AppUser appUser) {
		List<Comment> comments = commentRepository.findByAppUser(appUser);
		return comments;
	}

	@Override
	public Keyword saveKeyword(Keyword keyword) {
		keyword = keywordRepository.save(keyword);
		return keyword;
	}

}
