package com.dream.app.service;

import java.util.List;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;
import com.dream.app.transferobject.CommentDTO;
import com.dream.app.transferobject.DreamPostDTO;

public interface DreamPostService {
	
	List<DreamPost> findPostByAppUser(AppUser appUser);
	List<DreamPost> getAllPosts();
	DreamPost createPost(DreamPostDTO postDTO);
	DreamPost updatePost(DreamPostDTO postDTO);
	List<DreamPost> findPostsByKeywords(List<String> keyrowds, long userId);
	void deletePostByPostId(Long postId);
	Comment addComment(CommentDTO commentDTO);
	Keyword saveKeyword(Keyword keyword);
	Keyword getKeywordByName(String name);
	List<Comment> getCommentsByDreamPostId(Long postId);
	List<Comment> getCommentsByAppUserId(Long userId);
	void deleteComment(Long commentId);

}
