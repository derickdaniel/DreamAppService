package com.dream.app.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;
import com.dream.app.exception.DreamPostNotFountException;
import com.dream.app.repository.CommentRepository;
import com.dream.app.repository.DreamPostRepository;
import com.dream.app.repository.KeywordRepository;
import com.dream.app.transferobject.CommentDTO;
import com.dream.app.transferobject.DreamPostDTO;

@Service
@Transactional
public class DreamPostServiceImpl implements DreamPostService {

	private final DreamPostRepository dreamPostRepository;
	private final CommentRepository commentRepository; 
	private final KeywordRepository keywordRepository;
	private final AppUserService appUserService;
	
	@Autowired
	public DreamPostServiceImpl(final DreamPostRepository dreamPostRepository, final CommentRepository commentRepository, 
			final KeywordRepository keywordRepository, final AppUserService appUserService) {
		this.dreamPostRepository = dreamPostRepository;
		this.commentRepository = commentRepository;
		this.keywordRepository = keywordRepository;
		this.appUserService = appUserService;
	}
	
	@Override
	public List<DreamPost> findPostByAppUser(AppUser appUser) {
		List<DreamPost> dreams = dreamPostRepository.findByAppUser(appUser);
		return dreams;
	}

	@Override
	public DreamPost createPost(DreamPostDTO postDTO) {
		DreamPost post = postDTO.populateDreamPost();
		AppUser user = appUserService.getUserByUserId(postDTO.getAppUserId());
		post.setAppUser(user);
		post.setCreatedDate(new Timestamp(new Date().getTime()));
		post.setModifiedDate(new Timestamp(new Date().getTime()));
		
		saveKeywordsToPost(postDTO, post);		
		post =  dreamPostRepository.save(post);
		return post;
	}

	private void saveKeywordsToPost(DreamPostDTO postDTO, DreamPost post) {
		Set<Keyword> keywords = new HashSet<Keyword>();
		String[] keywordList = postDTO.getKeywordsList();
		if(keywordList != null && keywordList.length != 0) {
			for(String keyword : keywordList) {
				if(keyword != null && keyword.length() != 0) {
					Keyword keywordObj = this.getKeywordByName(keyword.toLowerCase());
					if(keywordObj != null) {
						keywords.add(keywordObj);
						post.setKeywords(keywords);
					}else {
						Keyword newKeyword = new Keyword();
						newKeyword.setName(keyword.toLowerCase());
						Set<DreamPost> dreamPostSet = new HashSet<DreamPost>();
						dreamPostSet.add(post);
						newKeyword.setDreamPosts(dreamPostSet); //test
						keywords.add(newKeyword);
						this.saveKeyword(newKeyword);
						post.setKeywords(keywords);
					}
				}
			}
		}
	}
	
	@Override
	public DreamPost updatePost(DreamPostDTO postDTO) {
		AppUser user = appUserService.getUserByUserId(postDTO.getAppUserId());
		DreamPost existingPost = dreamPostRepository.findById(postDTO.getPostId()).orElseThrow(() -> new DreamPostNotFountException("post not found with post id: "+postDTO.getPostId()));
		DreamPost updatePost = postDTO.populateDreamPost();
		updatePost.setAppUser(user);
		updatePost.setCreatedDate(existingPost.getCreatedDate());
		updatePost.setModifiedDate(new Timestamp(new Date().getTime()));
		saveKeywordsToPost(postDTO, updatePost);
		updatePost =  dreamPostRepository.save(updatePost);
		return updatePost;
	}
	
	@Override
	public Comment addComment(CommentDTO commentDTO) {
		Comment comment = commentDTO.populateComment();
		AppUser user = appUserService.getUserByUserId(commentDTO.getUserId());
		DreamPost existingPost = dreamPostRepository.findById(commentDTO.getPostId()).orElseThrow(() -> new DreamPostNotFountException("post not found with post id: "+commentDTO.getPostId()));
		comment.setAppUser(user);
		comment.setDreamPost(existingPost);
		comment.setCreatedDate(new Timestamp(new Date().getTime()));
		comment = commentRepository.save(comment);
		return comment;
	}

	@Override
	public List<Comment> getCommentsByDreamPostId(Long postId) {
		DreamPost existingPost = dreamPostRepository.findById(postId).orElseThrow(() -> new DreamPostNotFountException("post not found with post id: "+postId));
		List<Comment> comments = commentRepository.findByDreamPost(existingPost);
		return comments;
	}

	@Override
	public List<Comment> getCommentsByAppUserId(Long userId) {
		AppUser user = appUserService.getUserByUserId(userId);
		List<Comment> comments = commentRepository.findByAppUser(user);
		return comments;
	}

	@Override
	public Keyword saveKeyword(Keyword keyword) {
		keyword = keywordRepository.save(keyword);
		return keyword;
	}

	@Override
	public void deletePostByPostId(Long postId) {
		DreamPost existingPost = dreamPostRepository.findById(postId).orElseThrow(() -> new DreamPostNotFountException("post not found with post id: "+postId));
		dreamPostRepository.delete(existingPost);
	}

	@Override
	public Keyword getKeywordByName(String name) {
		Keyword keyword = keywordRepository.findByName(name).orElse(null);
		return keyword;
	}

	@Override
	public List<DreamPost> findPostsByKeywords(List<String> keywords, long userId) {
		appUserService.getUserByUserId(userId);
		List<DreamPost> matchedDreams = new ArrayList<DreamPost>();
		if(keywords != null && !keywords.isEmpty()) {
			for(String keyword : keywords) {
				if(keyword != null && keyword.length() != 0) {
					List<DreamPost> matchedDreamPerKeyword = dreamPostRepository.findByKeywords_nameAndAppUser_userIdNot(keyword, userId);
					matchedDreams.addAll(matchedDreamPerKeyword);
				}
			}
		}		
		return matchedDreams;
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	@Override
	public List<DreamPost> getAllPosts() {
		return dreamPostRepository.findAll();
	}

}
