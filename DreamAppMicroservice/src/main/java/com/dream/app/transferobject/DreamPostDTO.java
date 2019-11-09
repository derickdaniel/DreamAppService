package com.dream.app.transferobject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
public class DreamPostDTO {

	private Long postId;
	private Long appUserId;
	private String title;
	private String description;
	private AppUser appUser;
	private Date createdDate;
	private Date modifiedDate;
	@JsonBackReference
	private Set<Keyword> keywords = new HashSet<>();
	private String[] keywordsList;


	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public String[] getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(String[] keywordsList) {
		this.keywordsList = keywordsList;
	}

	public DreamPost populateDreamPost() {
		DreamPost dreamPost = new DreamPost();
		dreamPost.setTitle(this.getTitle());
		dreamPost.setDescription(this.getDescription());
		dreamPost.setKeywords(this.getKeywords());
		dreamPost.setAppUser(this.getAppUser());
		dreamPost.setPostId(this.getPostId());
		dreamPost.setAppUser(this.appUser);
		return dreamPost;
	}

	public DreamPostDTO populateDreamPostDTO(DreamPost dreamPost) {
		DreamPostDTO dreamPostDTO = new DreamPostDTO();
		if (dreamPost != null) {
			dreamPostDTO.setTitle(dreamPost.getTitle());
			dreamPostDTO.setDescription(dreamPost.getDescription());
			dreamPostDTO.setKeywords(dreamPost.getKeywords());
			dreamPostDTO.setAppUser(dreamPost.getAppUser());
			dreamPostDTO.setPostId(dreamPost.getPostId());
			dreamPostDTO.setAppUserId(dreamPost.getAppUser().getUserId());
			dreamPostDTO.setCreatedDate(dreamPost.getCreatedDate());
			dreamPostDTO.setModifiedDate(dreamPost.getModifiedDate());
			if(dreamPost.getKeywords() != null) {
				int count = 0;
				String[] keywordsList = new String[dreamPost.getKeywords().size()];
				for(Keyword k : dreamPost.getKeywords()) {
					keywordsList[count] = k.getName();
					count++;
				}
				dreamPostDTO.setKeywordsList(keywordsList);
			}
		}
		return dreamPostDTO;
	}
}
