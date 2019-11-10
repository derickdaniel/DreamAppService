package com.dream.app.transferobject;

import java.util.Date;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class CommentDTO {
	
    private Long commentId;
    
    private Long userId;
    
    private Long postId;

    private String text;

    @JsonBackReference
    private DreamPost dreamPost;
    
    private AppUser appUser;

	private Date createdDate;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DreamPost getDreamPost() {
		return dreamPost;
	}

	public void setDreamPost(DreamPost dreamPost) {
		this.dreamPost = dreamPost;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Comment populateComment() {
		Comment comment = new Comment();
		comment.setCommentId(this.getCommentId());
		comment.setAppUser(this.getAppUser());
		comment.setText(this.getText());
		comment.setDreamPost(this.getDreamPost());
		comment.setCreatedDate(this.getCreatedDate());
		return comment;
	}

}
