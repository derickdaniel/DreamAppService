package com.dream.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	
	@Id
    @GeneratedValue
    @Column(name = "Comment_Id", nullable = false)
    private Long commentId;

    @NotNull
    @Lob
    @Column(name = "Text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Dream_Post_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private DreamPost dreamPost;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "App_User_Id", nullable = false)
    //@JsonIgnore
    private AppUser appUser;
    
    @Column(name = "Created_Date", length = 36, nullable = false)
	private Date createdDate;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
  
}
